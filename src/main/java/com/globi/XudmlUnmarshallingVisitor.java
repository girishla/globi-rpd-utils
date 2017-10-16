package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationTable;
import com.globi.rpd.xudml.ResourceFactory;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlMarshaller;

import lombok.extern.slf4j.Slf4j;
import xudml.PresentationCatalogW;
import xudml.PresentationTableW;

@Slf4j
public class XudmlUnmarshallingVisitor extends BaseVisitor<Object, Exception> {

	@Override
	public PresentationCatalog visit(PresentationCatalog presCatalog) throws Exception {

		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();

		log.info("*******************Unmarshalling PresentationCatalog from file: " + presCatalog.getResourceUri());
		
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
	public PresentationTable visit(PresentationTable presTable) throws Exception {
	
		XudmlMarshaller<PresentationTableW> marshaller = new XudmlMarshaller<PresentationTableW>();

		log.info("================================Unmarshalling PresentationTable from file: " + presTable.getResourceUri());
		
		presTable.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL("file:" + presTable.getResourceUri())));
		
		
		return presTable;
		
	}

}
