package com.globi.rpd;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationTable;
import com.globi.rpd.xudml.ResourceFactory;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlMarshaller;

import lombok.extern.slf4j.Slf4j;
import xudml.PresentationCatalogW;
import xudml.PresentationTableW;

@Slf4j
public class XudmlUnmarshallingOperator extends BaseOperator<Object, Exception> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) throws Exception {

		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();

		
		presCatalog.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL(presCatalog.getResourceUri())));
		
		presCatalog.getXudmlObject()//
		.getRefTables()//
		.getRefPresentationTable()//
		.stream()//
		.forEach(table -> {

			log.debug(table.getPresentationTableRef());
			presCatalog.getPresentationTables()
					.add(new PresentationTable(XudmlConstants.XUDML_BASEURL + table.getPresentationTableRef().split("#")[0]));

		});
		

		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) throws Exception {
	
		XudmlMarshaller<PresentationTableW> marshaller = new XudmlMarshaller<PresentationTableW>();

		presTable.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL("file:" + presTable.getResourceUri())));
		
		
		return presTable;
		
	}

}
