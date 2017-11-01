package com.globi.rpd.dsl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.globi.rpd.CatalogDefaultTraverser;
import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.operator.CatalogTraversingOperator;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

/**
 * The builder implementation for the fluent method chain
 * 
 * @author Girish Lakshmanan
 *
 */
public class StandardRpdBuilder {

	public CatalogStep init() {
		return new RpdSteps();
	}

	StandardRpdBuilder() {
	}

	public interface CatalogStep {
		CatalogStep catalog(XudmlFolder folder);

		GetStep noMoreCatalogs();
	}

	public interface GetStep {
		StandardRpd get();
	}

	public static class RpdSteps implements CatalogStep, GetStep {

		private StandardRpd rpd;
		private final Set<PresentationCatalog> catalogObjects = new HashSet<PresentationCatalog>();
		private final Set<BusinessModel> modelObjects = new HashSet<BusinessModel>();
		private final Set<Database> physicalObjects = new HashSet<Database>();

		public CatalogStep catalog(XudmlFolder folder) {

			List<String> fileList = folder.getResources().stream().map(resource -> resource.getFilename())//
					.collect(Collectors.toList());

			for (String fileName : fileList) {

				PresentationCatalog presCatalog = PresentationCatalog.fromResource("file:\\" +XudmlConstants.XUDML_BASEURL + XudmlConstants.XUDML_CATALOGURL + fileName);

				XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
				CatalogTraversingOperator<Object> tv = new CatalogTraversingOperator<Object>(new CatalogDefaultTraverser(),
						unmarshalOperator);
				tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
				presCatalog.apply(tv);

				HydratingOperator hydratingOperator = new HydratingOperator();
				CatalogTraversingOperator<Object> tv2 = new CatalogTraversingOperator<Object>(new CatalogDefaultTraverser(),
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

	}

}
