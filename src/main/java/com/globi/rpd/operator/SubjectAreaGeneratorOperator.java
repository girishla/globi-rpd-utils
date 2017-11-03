package com.globi.rpd.operator;

import java.util.UUID;

import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;

import xudml.PresentationCatalogW;
import xudml.RefTablePresentationCatalogTableT;

public class SubjectAreaGeneratorOperator implements Operator<PresentationCatalog> {
	
	@Override
	public PresentationCatalog operate(LogicalTable table) {
	
		if(table.getXudmlObject()==null)
			throw new IllegalStateException("SubjectAreaGeneratorOperator: Cannot generate subject area without a XUDML instance set");
		
		
		String newcatalogId="m" + UUID.randomUUID().toString();
		PresentationCatalog catalog=new PresentationCatalog(newcatalogId);
		
		PresentationCatalogW xudmlObject=new PresentationCatalogW();
		xudmlObject.setMdsid(newcatalogId);
		xudmlObject.setName("Autogen - " + table.getName());
		xudmlObject.setHasDispName(false);
		xudmlObject.setHasDispDescription(false);
		xudmlObject.setIsAutoAggr(false);
		xudmlObject.setSubjectAreaRef(table.getXudmlObject().getSubjectAreaRef());
		RefTablePresentationCatalogTableT emptyRefTable=new RefTablePresentationCatalogTableT();
		xudmlObject.setRefTables(emptyRefTable);
		catalog.setXudmlObject(xudmlObject);
		
//		for(LogicalColumnW column:table.getXudmlObject().getLogicalColumn()){
//			table.getLogicalColumns().add(new LogicalColumn(column));
//		}
		
		return catalog;
		
	}
	

}
