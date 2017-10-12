package com.globi.rpd.xudml;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xudml.AliasW;
import xudml.ObjectFactory;
import xudml.PresentationCatalogW;

@Data
@Slf4j
public class PresentationCatalog {

	private PresentationCatalogW xudmlObject;
	
	
	private final List<PresentationTable> presentationTables = new ArrayList<PresentationTable>();

	public static PresentationCatalog fromFile(Resource resource) {

		PresentationCatalog pcat = new PresentationCatalog();
		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
		pcat.setXudmlObject(marshaller.unmarshall(resource));
		pcat.xudmlObject.getRefTables().getRefPresentationTable().stream().forEach(table -> {

			log.debug(table.getPresentationTableRef());
			pcat.presentationTables.add(PresentationTable.fromFile(ResourceFactory.fromURL(XudmlConstants.XUDML_BASEURL + table.getPresentationTableRef())));

		});
		
		
		String dispName=pcat.xudmlObject.getName().replace("Global Reporting - Measures - ", "");
		pcat.xudmlObject.setHasDispName(true);
		
		AliasW alias=new AliasW();
		alias.setName(dispName);
		pcat.xudmlObject.getAlias().add(alias);
		pcat.xudmlObject.setDispName(dispName);
		
		ObjectFactory factory = new ObjectFactory();
		
//		factory.createPresentationCatalog(pcat.xudmlObject);
		
		marshaller.marshall(resource,factory.createPresentationCatalog(pcat.xudmlObject));

		
		return pcat;
	}

	

}
