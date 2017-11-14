package com.globi.rpd.dsl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.globi.rpd.AppProperties;
import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.StandardRpd;
import com.globi.rpd.operator.BreadthFirstTraversingOperator;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;
import com.globi.rpd.operator.ResolveLogicalJoinsOperator;
import com.globi.rpd.operator.XudmlMarshallingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.traverser.DefaultTraverser;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

/**
 * The builder implementation for the fluent method chain
 * 
 * @author Girish Lakshmanan
 *
 */
//@Slf4j
public class StandardRpdBuilder {

	public SetRepoPathStep init() {
		return new RpdSteps();
	}

	StandardRpdBuilder() {
	}

	public interface SetRepoPathStep {
		MutateStep setRepoPath(String path);
	}

	public interface MutateStep {
		MutateStep loadCatalog();
		MutateStep loadModel();
		MutateStep loadDatabase();

		MutateStep applyRpdOperator(Class<? extends Operator<StandardRpd>> cl);
		MutateStep applyOperatorToAllCatalogs(Class<? extends Operator<RpdComponent>> cl);

		SaveStep noMoreWork();

	}

	public interface GetStep {
		StandardRpd get();
	}

	public interface SaveStep {
		GetStep nothingToSave();

		GetStep save(String path);
	}

	public static class RpdSteps implements SetRepoPathStep, MutateStep, GetStep, SaveStep {

		private StandardRpd rpd;
		private final Set<PresentationCatalog> catalogObjects = new HashSet<PresentationCatalog>();
		private final Set<BusinessModel> modelObjects = new HashSet<BusinessModel>();
		private final Set<Database> physicalObjects = new HashSet<Database>();
		private final Map<XudmlFolder.FolderType, XudmlFolder> folders = new HashMap<>();
		private String repoPath;

		public MutateStep loadCatalog() {
			
			XudmlFolder folder=new XudmlFolder(this.repoPath + XudmlConstants.XUDML_CATALOGURL);

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
		public MutateStep loadModel() {

			XudmlFolder folder=new XudmlFolder(this.repoPath + XudmlConstants.XUDML_MODELURL);
			
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
		
		
		public MutateStep loadDatabase() {
			
			XudmlFolder folder=new XudmlFolder(this.repoPath + XudmlConstants.XUDML_DATABASEURL);

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

				Database db = Database.fromResource(resourceUri);
				this.hydrate(db, resourceUri);
				this.physicalObjects.add(db);

			}

			folders.put(XudmlFolder.FolderType.CATALOG, folder);

			return this;
		}

		private void hydrate(Operable<? extends RpdComponent> rpdComponent, String ResourceName) {

			XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
			BreadthFirstTraversingOperator tv = new BreadthFirstTraversingOperator(new DefaultTraverser(),
					unmarshalOperator);
			tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
			rpdComponent.apply(tv);

			
			
			HydratingOperator hydratingOperator = new HydratingOperator();
			BreadthFirstTraversingOperator tv2 = new BreadthFirstTraversingOperator(new DefaultTraverser(),
					hydratingOperator);
			tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
			rpdComponent.apply(tv2);

		}

		/**
		 * Applies an operator to the whole Rpd Tree
		 */
		@Override
		public MutateStep applyRpdOperator(Class<? extends Operator<StandardRpd>> cl) {

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
		public GetStep save(String basePath) {

			for (BusinessModel model : this.modelObjects) {

				model.setResourceUri(basePath + XudmlConstants.XUDML_MODELURL + model.getId() + ".xml");

				XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
				BreadthFirstTraversingOperator tv = new BreadthFirstTraversingOperator(new DefaultTraverser(),
						marshallingOperator);
				tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
				model.apply(tv);

			}

			
			//TODO Move this looping logic to it's own UriChangeOperator
			//Only need this when basepath is diff to the current Uri - like a "Save As"
			for (PresentationCatalog catalog : this.catalogObjects) {

				catalog.setResourceUri(basePath + XudmlConstants.XUDML_CATALOGURL + catalog.getId() + ".xml");

				for (PresentationTable table : catalog.getPresentationTables()) {
					table.setResourceUri(basePath + XudmlConstants.XUDML_PRESTABLEURL + table.getId() + ".xml");

					
					for (PresentationHierarchy hierarchy : table.getPresentationHierarchies()) {
						table.setResourceUri(basePath + XudmlConstants.XUDML_PRESHIERARCHY + hierarchy.getId() + ".xml");

					}
					
				}
				XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
				BreadthFirstTraversingOperator tv = new BreadthFirstTraversingOperator(new DefaultTraverser(),
						marshallingOperator);
				tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
				catalog.apply(tv);


			}



			return this;
		}

		@Override
		public GetStep nothingToSave() {
			return this;
		}

		@Override
		public MutateStep setRepoPath(String path) {
			
			File repoModelDirectory=new File(path + XudmlConstants.XUDML_MODELURL);
			
			if (!(repoModelDirectory.exists())) {
				throw new IllegalArgumentException("Supplied Path does not appear to be a valid RPD XML Folder@ " + path);
			}
			
			this.repoPath = path;
			AppProperties.INSTANCE.setBasePath(path);
			return this;
		}

		@Override
		public MutateStep applyOperatorToAllCatalogs(Class<? extends Operator<RpdComponent>> cl) {

			// Instantiate the strategy
			Operator<RpdComponent> strategy = null;
			try {
				strategy = cl.newInstance();
			} catch (IllegalAccessException e) {
				System.err.println("Class not accessible: " + cl);
				throw new IllegalArgumentException(" Operator Class not accessible");
			} catch (InstantiationException e) {
				System.err.println("Class not instantiable: " + cl);
				throw new IllegalArgumentException("Operator Class not instantiable");
			}
		
			
			
			for(PresentationCatalog catalog:this.catalogObjects){
				strategy.operate(catalog);
				DefaultLoggerProgressMonitor logger = new DefaultLoggerProgressMonitor();
				logger.operated(cl.getName(), catalog);
			}

			return this;

		}

	}

}
