package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;

import xudml.LogicalColumnW;
import xudml.PresentationColumnW;


public class HydratingOperator implements Operator<RpdComponent> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		if(presCatalog.getXudmlObject()==null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");
		


		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {
	
		if(presTable.getXudmlObject()==null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");
		
		
		for(PresentationColumnW column:presTable.getXudmlObject().getPresentationColumn()){
			presTable.getPresentationColumns().add(new PresentationColumn(column));
		}
		
		
		
		return presTable;
		
	}
	
	
	@Override
	public BusinessModel operate(BusinessModel table) {
	
		if(table.getXudmlObject()==null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");
		
		//nothing to hydrate

		return table;
		
	}
	
	@Override
	public LogicalTable operate(LogicalTable table) {
	
		if(table.getXudmlObject()==null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");
		
		
		for(LogicalColumnW column:table.getXudmlObject().getLogicalColumn()){
			table.getLogicalColumns().add(new LogicalColumn(column));
		}
		
		return table;
		
	}

}
