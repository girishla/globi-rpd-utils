package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationTable;

import lombok.extern.slf4j.Slf4j;
import xudml.AliasW;

@Slf4j
public class NameModificationVisitor extends BaseVisitor<Object, Exception> {

	@Override
	public PresentationCatalog visit(PresentationCatalog presCatalog) throws Exception {

		if(presCatalog.getXudmlObject()==null)
			throw new RuntimeException("Cannot process without a XUDML instance set");
		
		AliasW alias=new AliasW();
		alias.setName(presCatalog.getXudmlObject().getName());
		presCatalog.getXudmlObject().getAlias().add(alias);
		presCatalog.getXudmlObject().setDispName(presCatalog.getXudmlObject().getName().replaceAll("Global Reporting - Measures - ", ""));

		return presCatalog;
	}

	
	
	@Override
	public PresentationTable visit(PresentationTable presTable) throws Exception {
	
		if(presTable.getXudmlObject()==null)
			throw new RuntimeException("Cannot process withour a XUDML instance set");
		
		//nothing to Modify yet
		
		
		return presTable;
		
	}

}
