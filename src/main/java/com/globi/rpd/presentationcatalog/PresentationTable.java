package com.globi.rpd.presentationcatalog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.Resource;

import com.globi.Visitable;
import com.globi.Visitor;
import com.globi.rpd.xudml.ResourceFactory;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlMarshaller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xudml.PresentationTableT;

@Data
@Slf4j
public class PresentationTable     implements Visitable{

	private PresentationTableT xudmlObject;
	private final String resourceUri;
	
	private final List<PresentationColumn> presentationColumns = new ArrayList<PresentationColumn>();


	public PresentationTable(String resourceUri){
		this.resourceUri=resourceUri;
		
	}
	
	
	@Override
    public<R, E extends Throwable >R accept(Visitor<R, E> aVisitor)
            throws E
        {
            return aVisitor.visit(this);
        }
	
	
}
