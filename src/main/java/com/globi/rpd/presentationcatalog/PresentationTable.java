package com.globi.rpd.presentationcatalog;

import java.util.ArrayList;
import java.util.List;

import com.globi.Visitable;
import com.globi.Operator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xudml.PresentationTableW;

@Data
@Slf4j
public class PresentationTable     implements Visitable{

	private PresentationTableW xudmlObject;
	private String resourceUri;
	
	private final List<PresentationColumn> presentationColumns = new ArrayList<PresentationColumn>();


	public PresentationTable(String resourceUri){
		this.resourceUri=resourceUri;
		
	}
	
	
	@Override
    public<R, E extends Throwable >R accept(Operator<R, E> anOperator)
            throws E
        {
            return anOperator.operate(this);
        }
	
	
}
