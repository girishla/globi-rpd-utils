package com.globi.rpd.operator;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.xudml.XudmlMarshaller;

import xudml.ObjectFactory;
import xudml.PresentationCatalogW;

public class XudmlMarshallingOperator extends BaseOperator<Object, Exception> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) throws Exception {

		if(presCatalog.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(presCatalog.getResourceUri(),factory.createPresentationCatalog(presCatalog.getXudmlObject()));

		
		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) throws Exception {
	
		if(presTable.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(presTable.getResourceUri(),factory.createPresentationTable(presTable.getXudmlObject()));
		
		
		return presTable;
		
	}

}
