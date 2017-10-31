package com.globi.rpd.presentationcatalog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.globi.rpd.Operable;
import com.globi.rpd.Operator;
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
	
	@Override
    public<R, E extends Throwable >R apply(Operator<R, E> anOperator)
            throws E
        {
            return anOperator.operate(this);
        }
	
	
	@Override
	public String toString(){
		
		return  "  Subject Area:" +  xudmlObject.getName();
		
	}
	

}
