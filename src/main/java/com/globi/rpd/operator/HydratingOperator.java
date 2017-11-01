package com.globi.rpd.operator;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

import xudml.PresentationColumnW;


public class HydratingOperator extends BaseOperator<Object, Exception> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) throws Exception {

		if(presCatalog.getXudmlObject()==null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");
		


		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) throws Exception {
	
		if(presTable.getXudmlObject()==null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");
		
		
		for(PresentationColumnW column:presTable.getXudmlObject().getPresentationColumn()){
			presTable.getPresentationColumns().add(new PresentationColumn(column));
		}
		
		
		
		return presTable;
		
	}

}
