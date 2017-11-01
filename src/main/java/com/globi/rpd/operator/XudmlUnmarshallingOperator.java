package com.globi.rpd.operator;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.xudml.ResourceFactory;
import com.globi.rpd.xudml.XudmlMarshaller;

import xudml.PresentationCatalogW;
import xudml.PresentationTableW;

public class XudmlUnmarshallingOperator extends BaseOperator<Object> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();

		
		presCatalog.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL(presCatalog.getResourceUri())));
		
		presCatalog.getXudmlObject()//
		.getRefTables()//
		.getRefPresentationTable()//
		.stream()//
		.forEach(table -> {
			presCatalog.getPresentationTables()
					.add(new PresentationTable(table.getRefId()));

		});
		

		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {
	
		XudmlMarshaller<PresentationTableW> marshaller = new XudmlMarshaller<PresentationTableW>();

		presTable.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL("file:" + presTable.getResourceUri())));
		
		
		return presTable;
		
	}

}
