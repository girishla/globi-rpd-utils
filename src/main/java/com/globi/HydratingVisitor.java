package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.extern.slf4j.Slf4j;
import xudml.PresentationColumnW;

@Slf4j
public class HydratingVisitor extends BaseVisitor<Object, Exception> {

	@Override
	public PresentationCatalog visit(PresentationCatalog presCatalog) throws Exception {

		if(presCatalog.getXudmlObject()==null)
			throw new RuntimeException("Cannot hydrate withour a XUDML instance set");
		


		return presCatalog;
	}

	@Override
	public PresentationTable visit(PresentationTable presTable) throws Exception {
	
		if(presTable.getXudmlObject()==null)
			throw new RuntimeException("Cannot hydrate withour a XUDML instance set");
		
		
		for(PresentationColumnW column:presTable.getXudmlObject().getPresentationColumn()){
			presTable.getPresentationColumns().add(new PresentationColumn(column));
		}
		

		
		return presTable;
		
	}

}
