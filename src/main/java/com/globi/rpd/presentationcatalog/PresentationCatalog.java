package com.globi.rpd.presentationcatalog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.globi.Operable;
import com.globi.Operator;
import com.globi.rpd.xudml.ResourceFactory;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlMarshaller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xudml.AliasW;
import xudml.ObjectFactory;
import xudml.PresentationCatalogW;

@Data
@Slf4j
public class PresentationCatalog  implements Operable{

	private String resourceUri;
	private PresentationCatalogW xudmlObject;
	
	private final List<PresentationTable> presentationTables = new ArrayList<PresentationTable>();
	
	public PresentationCatalog(String resourceUri){
		
		this.resourceUri=resourceUri;
		
	}
	
	
	

//	public static PresentationCatalog fromFile(Resource resource) {
//
//		PresentationCatalog pcat = new PresentationCatalog();
//		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
//		
//		pcat.setXudmlObject(marshaller.unmarshall(resource));
//		
//
//		
//		String dispName=pcat.xudmlObject.getName().replace("Global Reporting - Measures - ", "");
//		pcat.xudmlObject.setHasDispName(true);
//		
//		AliasW alias=new AliasW();
//		alias.setName(dispName);
//		pcat.xudmlObject.getAlias().add(alias);
//		pcat.xudmlObject.setDispName(dispName);
//		
//		ObjectFactory factory = new ObjectFactory();
//		
//		
//		marshaller.marshall(resource,factory.createPresentationCatalog(pcat.xudmlObject));
//
//		
//		return pcat;
//	}

	@Override
    public<R, E extends Throwable >R apply(Operator<R, E> anOperator)
            throws E
        {
            return anOperator.operate(this);
        }
	

}
