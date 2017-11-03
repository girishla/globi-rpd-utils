package com.globi.rpd.dsl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.operator.CatalogTraversingOperator;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.traverser.CatalogDefaultTraverser;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

/**
 * The builder implementation for the fluent method chain
 * 
 * @author Girish Lakshmanan
 *
 */
public class StandardRpdBuilder {

	public HydrateStep init() {
		return new RpdSteps();
	}

	StandardRpdBuilder() {
	}

	public interface HydrateStep {
		HydrateStep catalog(XudmlFolder folder);

		HydrateStep model(XudmlFolder folder);

		GetStep noMoreCatalogs();
	}

	public interface GetStep {
		StandardRpd get();
	}

	public static class RpdSteps implements HydrateStep, GetStep {

		private StandardRpd rpd;
		private final Set<PresentationCatalog> catalogObjects = new HashSet<PresentationCatalog>();
		private final Set<BusinessModel> modelObjects = new HashSet<BusinessModel>();
		private final Set<Database> physicalObjects = new HashSet<Database>();

		public HydrateStep catalog(XudmlFolder folder) {

			List<String> fileList = folder.getResources()
					.stream()
					.map(resource -> resource.getFilename())//
					.collect(Collectors.toList());

			for (String fileName : fileList) {
				
				this.hydrate(
						"file:\\" + XudmlConstants.XUDML_BASEURL + XudmlConstants.XUDML_CATALOGURL + fileName);

				PresentationCatalog presCatalog = PresentationCatalog.fromResource(
						"file:\\" + XudmlConstants.XUDML_BASEURL + XudmlConstants.XUDML_CATALOGURL + fileName);

				XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
				CatalogTraversingOperator tv = new CatalogTraversingOperator(new CatalogDefaultTraverser(),
						unmarshalOperator);
				tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
				presCatalog.apply(tv);

				HydratingOperator hydratingOperator = new HydratingOperator();
				CatalogTraversingOperator tv2 = new CatalogTraversingOperator(new CatalogDefaultTraverser(),
						hydratingOperator);
				tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
				presCatalog.apply(tv2);

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
		public GetStep noMoreCatalogs() {
			return this;
		}

		@Override
		public HydrateStep model(XudmlFolder folder) {
			// TODO Auto-generated method stub
			return null;
		}

		private PresentationCatalog hydrate(String ResourceName) {

			PresentationCatalog presCatalog = PresentationCatalog.fromResource(ResourceName);

			XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
			CatalogTraversingOperator tv = new CatalogTraversingOperator(new CatalogDefaultTraverser(),
					unmarshalOperator);
			tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv);

			HydratingOperator hydratingOperator = new HydratingOperator();
			CatalogTraversingOperator tv2 = new CatalogTraversingOperator(new CatalogDefaultTraverser(),
					hydratingOperator);
			tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv2);

			return presCatalog;
		}

	}

}
