package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationTable;
import com.globi.rpd.xudml.XudmlMarshaller;

import lombok.extern.slf4j.Slf4j;
import xudml.ObjectFactory;
import xudml.PresentationCatalogW;

@Slf4j
public class XudmlMarshallingOperator extends BaseOperator<Object, Exception> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) throws Exception {

		if(presCatalog.getXudmlObject()==null)
			throw new RuntimeException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(presCatalog.getResourceUri(),factory.createPresentationCatalog(presCatalog.getXudmlObject()));

		
		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) throws Exception {
	
		if(presTable.getXudmlObject()==null)
			throw new RuntimeException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(presTable.getResourceUri(),factory.createPresentationTable(presTable.getXudmlObject()));
		
		
		return presTable;
		
	}

}
