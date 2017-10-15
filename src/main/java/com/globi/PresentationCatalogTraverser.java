
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
    public void traverse(PresentationCatalog aBean, Visitor<?, E> aVisitor)
        throws E
    {
    	
    	log.debug("----------------------------------");
    	log.debug("----------------------------------");
    	log.debug("----------------------------------" + aBean.getPresentationTables().size());
    	log.debug("----------------------------------" + aBean.getXudmlObject().getName());
    	
    	
        for (PresentationTable bean: aBean.getPresentationTables()) {
            bean.accept(aVisitor);
        }

    }

    @Override
    public void traverse(PresentationTable aBean, Visitor<?, E> aVisitor)
        throws E
    {
    	
    	
    	
        for (PresentationColumn bean: aBean.getPresentationColumns()) {
            bean.accept(aVisitor);
        }
        
    }

	@Override
	public void traverse(PresentationColumn aBean, Visitor<?, E> aVisitor) throws E {
		
		//no-op
		
	}
    
    
    
    

}
