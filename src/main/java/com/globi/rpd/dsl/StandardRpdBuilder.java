package com.globi.rpd.dsl;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;
import com.globi.rpd.operator.ResolveLogicalJoinsOperator;
import com.globi.rpd.operator.TraversingOperator;
import com.globi.rpd.operator.XudmlMarshallingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.traverser.DefaultTraverser;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

import lombok.extern.slf4j.Slf4j;

/**
 * The builder implementation for the fluent method chain
 * 
 * @author Girish Lakshmanan
 *
 */
@Slf4j
public class StandardRpdBuilder {

	public HydrateStep init() {
		return new RpdSteps();
	}

	StandardRpdBuilder() {
	}

	public interface HydrateStep {
		HydrateStep catalog(XudmlFolder folder);

		HydrateStep model(XudmlFolder folder);

		HydrateStep catalogFromModelOperator(Class<? extends Operator<PresentationCatalog>> cl,
				Predicate<BusinessModel> predicate);

		SaveStep noMoreWork();

	}

	public interface GetStep {
		StandardRpd get();
	}

	public interface SaveStep {
		GetStep nothingToSave();

		GetStep save();
	}

	public static class RpdSteps implements HydrateStep, GetStep, SaveStep {

		private StandardRpd rpd;
		private final Set<PresentationCatalog> catalogObjects = new HashSet<PresentationCatalog>();
		private final Set<BusinessModel> modelObjects = new HashSet<BusinessModel>();
		private final Set<Database> physicalObjects = new HashSet<Database>();

		public HydrateStep catalog(XudmlFolder folder) {

			List<File> fileList = folder.getResources()
					.stream()
					.map(resource -> {
						try {
							return resource.getFile();
						} catch (IOException e) {

							e.printStackTrace();
							throw new RuntimeException("Unexpected IO Exception. Aborting Operation.");
						}
					})
					.collect(Collectors.toList());

			for (File file : fileList) {
				String resourceUri = "file:" + file.getAbsolutePath();

				PresentationCatalog presCatalog = PresentationCatalog.fromResource(resourceUri);
				this.hydrate(presCatalog, resourceUri);
				this.catalogObjects.add(presCatalog);

			}

			return this;
		}

		public StandardRpd get() {
			if (this.rpd == null) {
				this.rpd = new StandardRpd(catalogObjects, modelObjects, physicalObjects);
			}

			return rpd;
		}

		@Override
		public SaveStep noMoreWork() {
			return this;
		}

		@Override
		public HydrateStep model(XudmlFolder folder) {

			List<File> fileList = folder.getResources()
					.stream()
					.map(resource -> {
						try {
							return resource.getFile();
						} catch (IOException e) {

							e.printStackTrace();
							throw new RuntimeException("Unexpected IO Exception. Aborting Operation.");
						}
					})
					.collect(Collectors.toList());

			for (File file : fileList) {
				String resourceUri = "file:" + file.getAbsolutePath();

				BusinessModel model = BusinessModel.fromResource(resourceUri);
				this.hydrate(model, resourceUri);

				/*
				 * Resolve all logical Joins into the model.
				 */
				ResolveLogicalJoinsOperator resolveJoinsOperator = new ResolveLogicalJoinsOperator();
				model.apply(resolveJoinsOperator);

				this.modelObjects.add(model);
			}

			return this;

		}

		private void hydrate(Operable<? extends RpdComponent> rpdComponent, String ResourceName) {

			XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
			TraversingOperator tv = new TraversingOperator(new DefaultTraverser(), unmarshalOperator);
			tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
			rpdComponent.apply(tv);

			HydratingOperator hydratingOperator = new HydratingOperator();
			TraversingOperator tv2 = new TraversingOperator(new DefaultTraverser(), hydratingOperator);
			tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
			rpdComponent.apply(tv2);

		}

		/**
		 * Transforms a Model Object to another Model Object
		 */
		@Override
		public HydrateStep catalogFromModelOperator(Class<? extends Operator<PresentationCatalog>> cl,
				Predicate<BusinessModel> predicate) {

			Set<BusinessModel> models = this.modelObjects.stream()
					.filter(predicate)
					.collect(Collectors.toSet());

			for (BusinessModel model : models) {

				// Instantiate the strategy
				Operator<PresentationCatalog> strategy = null;
				try {
					strategy = cl.newInstance();
				} catch (IllegalAccessException e) {
					System.err.println("Class not accessible: " + cl);
					throw new IllegalArgumentException(" Operator Class not accessible");
				} catch (InstantiationException e) {
					System.err.println("Class not instantiable: " + cl);
					throw new IllegalArgumentException("Operator Class not instantiable");
				}

				PresentationCatalog resultComponent = strategy.operate(model);
				this.catalogObjects.add(resultComponent);

				DefaultLoggerProgressMonitor logger = new DefaultLoggerProgressMonitor();
				logger.operated(cl.getName(), model);

			}

			return this;

		}

		@Override
		public GetStep save() {

			Set<BusinessModel> models = this.modelObjects;

			for (BusinessModel model : models) {

				model.setResourceUri(XudmlConstants.TEMP_DIR + "modeltest.xml");

				XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
				TraversingOperator tv = new TraversingOperator(new DefaultTraverser(), marshallingOperator);
				tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
				model.apply(tv);
			}

			return this;
		}

		@Override
		public GetStep nothingToSave() {
			return this;
		}

	}

}
