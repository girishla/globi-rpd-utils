package com.globi.rpd.dsl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;

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

		HydrateStep applyRpdOperator(Class<? extends Operator<StandardRpd>> cl);

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
		private final Map<XudmlFolder.FolderType, XudmlFolder> folders = new HashMap<>();

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

			folders.put(XudmlFolder.FolderType.CATALOG, folder);

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

				folders.put(XudmlFolder.FolderType.MODEL, folder);

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
		public HydrateStep applyRpdOperator(Class<? extends Operator<StandardRpd>> cl) {

			// Instantiate the strategy
			Operator<StandardRpd> strategy = null;
			try {
				strategy = cl.newInstance();
			} catch (IllegalAccessException e) {
				System.err.println("Class not accessible: " + cl);
				throw new IllegalArgumentException(" Operator Class not accessible");
			} catch (InstantiationException e) {
				System.err.println("Class not instantiable: " + cl);
				throw new IllegalArgumentException("Operator Class not instantiable");
			}
			StandardRpd rpdOperable = new StandardRpd(catalogObjects, modelObjects, physicalObjects);
			strategy.operate(rpdOperable);
			DefaultLoggerProgressMonitor logger = new DefaultLoggerProgressMonitor();
			logger.operated(cl.getName(), rpdOperable);

			return this;

		}

		@Override
		public GetStep save() {

			for (BusinessModel model : this.modelObjects) {

				model.setResourceUri(XudmlConstants.XUDML_COPYURL + XudmlConstants.XUDML_MODELURL + model.getId() + ".xml");

				XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
				TraversingOperator tv = new TraversingOperator(new DefaultTraverser(), marshallingOperator);
				tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
				model.apply(tv);

			}

			
			/**
			 * Delete any model files that aren't in context anymore
			 */
			XudmlFolder modelFolder = folders.get(XudmlFolder.FolderType.MODEL);
			modelFolder.getResources()
					.stream()
					.filter(resource -> {

						boolean delete = true;
						for (BusinessModel model : this.modelObjects) {
							delete = !model.getResourceUri()
									.endsWith(resource.getFilename());

						}
						return delete;
					})
					.forEach(resource -> {
						try {
							resource.getFile()
									.delete();
						} catch (IOException e) {
							e.printStackTrace();
							throw new RuntimeException("Error during delete of file " + resource.getFilename());
						}
					});

			for (PresentationCatalog catalog : this.catalogObjects) {

				catalog.setResourceUri(XudmlConstants.XUDML_COPYURL + XudmlConstants.XUDML_CATALOGURL + catalog.getId() + ".xml");
				
				XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
				TraversingOperator tv = new TraversingOperator(new DefaultTraverser(), marshallingOperator);
				tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
				catalog.apply(tv);
			}
			
			
			/**
			 * Delete any catalog files that aren't in context anymore
			 */
			XudmlFolder catFolder = folders.get(XudmlFolder.FolderType.CATALOG);
			catFolder.getResources()
					.stream()
					.filter(resource -> {

						boolean delete = true;
						for (PresentationCatalog model : this.catalogObjects) {
							delete = !model.getResourceUri()
									.endsWith(resource.getFilename());

						}
						return delete;
					})
					.forEach(resource -> {
						try {
							resource.getFile()
									.delete();
						} catch (IOException e) {
							e.printStackTrace();
							throw new RuntimeException("Error during deleting of file " + resource.getFilename());
						}
					});


			return this;
		}

		@Override
		public GetStep nothingToSave() {
			return this;
		}

	}

}
