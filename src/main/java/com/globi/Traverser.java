
package com.globi;


import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

public interface Traverser<E extends Throwable >{

    void traverse(PresentationCatalog aBean, Visitor<?, E> aVisitor)
            throws E
        ;

        void traverse(PresentationTable aBean, Visitor<?, E> aVisitor)
            throws E
        ;
        
        void traverse(PresentationColumn aBean, Visitor<?, E> aVisitor)
                throws E
            ;
        
        
        
        
}
