
package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

import lombok.extern.slf4j.Slf4j;


@Slf4j

public class PresentationCatalogTraverser<E extends Throwable >
    implements Traverser<E>
{


    @Override
    public void traverse(PresentationCatalog presCatalog, Operator<?, E> anOperator)
        throws E
    {
    	
    	log.debug("----------------------------------");
    	log.debug("----------------------------------");
    	log.debug("----------------------------------" + presCatalog.getPresentationTables().size());
    	log.debug("----------------------------------" + presCatalog.getXudmlObject().getName());
    	
    	
        for (PresentationTable presTable: presCatalog.getPresentationTables()) {
        	
        	log.debug("^^^^^^^^^^^^^^^^^^^^" + presTable.getResourceUri());
        	presTable.apply(anOperator);
        }

    }

    @Override
    public void traverse(PresentationTable presTable, Operator<?, E> anOperator)
        throws E
    {
    	
        for (PresentationColumn presColumn: presTable.getPresentationColumns()) {
        	presColumn.apply(anOperator);
        }
        
    }

	@Override
	public void traverse(PresentationColumn presColumn, Operator<?, E> anOperator) throws E {
		
		// nowhere to go from here.
		
	}
    
    
    
    

}
