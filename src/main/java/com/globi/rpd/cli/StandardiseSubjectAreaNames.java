package com.globi.rpd.cli;

import com.globi.rpd.CatalogDefaultTraverser;
import com.globi.rpd.CatalogTraversingOperator;
import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.HydratingOperator;
import com.globi.rpd.NameModificationOperator;
import com.globi.rpd.XudmlMarshallingOperator;
import com.globi.rpd.XudmlUnmarshallingOperator;
import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.xudml.XudmlConstants;

public class StandardiseSubjectAreaNames implements RpdObjectCommand<Boolean, String> {

	@Override
	public Boolean execute(String subjectAreaName) throws Exception {

		if(!subjectAreaName.toUpperCase().equals("ALL"))
			throw new IllegalArgumentException("Invalid Argument " + subjectAreaName );
		
				
		PresentationCatalog presCatalog = new PresentationCatalog("testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");

		XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
		CatalogTraversingOperator<Object, Exception> tv = new CatalogTraversingOperator<>(new CatalogDefaultTraverser<Exception>(),
				unmarshalOperator);
		tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(tv);

		HydratingOperator hydratingOperator = new HydratingOperator();
		CatalogTraversingOperator<Object, Exception> tv2 = new CatalogTraversingOperator<>(
				new CatalogDefaultTraverser<Exception>(), hydratingOperator);
		tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(tv2);
		
		
		
		NameModificationOperator renamingOperator = new NameModificationOperator();
		CatalogTraversingOperator<Object, Exception> traversingOperator = new CatalogTraversingOperator<>(
				new CatalogDefaultTraverser<Exception>(), renamingOperator);
		traversingOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(traversingOperator);

		
		presCatalog.setResourceUri(XudmlConstants.XUDML_OUTPUT + "40000456-6dc5-167d-806e-c0a838100000");

		XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
		CatalogTraversingOperator<Object, Exception> tv3 = new CatalogTraversingOperator<>(
				new CatalogDefaultTraverser<Exception>(), marshallingOperator);
		tv3.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(tv3);
		
				
		return true;
	}



}
