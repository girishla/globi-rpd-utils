package com.globi.rpd.cli;

import com.globi.rpd.CatalogDefaultTraverser;
import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.operator.CatalogTraversingOperator;
import com.globi.rpd.operator.DisplayNameModificationOperator;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.SortingOperator;
import com.globi.rpd.operator.XudmlMarshallingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.xudml.XudmlConstants;

public class StandardisePresentationCatalog implements RpdObjectCommand<Boolean, String> {

	@Override
	public String execute(String subjectAreaName) throws Exception {

		if (!subjectAreaName.toUpperCase().equals("ALL"))
			throw new IllegalArgumentException("Invalid Argument " + subjectAreaName);

		try {

			PresentationCatalog presCatalog = new PresentationCatalog(
					"testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");

			
			XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
			CatalogTraversingOperator<Object> tv = new CatalogTraversingOperator<Object>(new CatalogDefaultTraverser(),
					unmarshalOperator);
			
			
			tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv);

			HydratingOperator hydratingOperator = new HydratingOperator();
			CatalogTraversingOperator<Object> tv2 = new CatalogTraversingOperator<>(new CatalogDefaultTraverser(),
					hydratingOperator);
			tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv2);

			DisplayNameModificationOperator renamingOperator = new DisplayNameModificationOperator(
					name -> name.replaceAll("Global Reporting - Measures - ", ""));
			CatalogTraversingOperator<Object> traversingOperator = new CatalogTraversingOperator<>(
					new CatalogDefaultTraverser(), renamingOperator);
			traversingOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(traversingOperator);

			SortingOperator sortingOperator = new SortingOperator();
			CatalogTraversingOperator<Object> traversingSortingOperator = new CatalogTraversingOperator<>(
					new CatalogDefaultTraverser(), sortingOperator);
			traversingSortingOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(traversingSortingOperator);

			presCatalog.setResourceUri(XudmlConstants.XUDML_OUTPUT + "40000456-6dc5-167d-806e-c0a838100000.xml");

			XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
			CatalogTraversingOperator<Object> tv3 = new CatalogTraversingOperator<>(new CatalogDefaultTraverser(),
					marshallingOperator);
			tv3.setProgressMonitor(new DefaultLoggerProgressMonitor());
			presCatalog.apply(tv3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "COMMAND PROCESSED.";
	}

}
