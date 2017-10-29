
package com.globi;


import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

public interface Traverser<E extends Throwable >{

    void traverse(PresentationCatalog aBean, Operator<?, E> anOperator)
            throws E;

        void traverse(PresentationTable aBean, Operator<?, E> anOperator)
            throws E;
        
        void traverse(PresentationColumn aBean, Operator<?, E> anOperator)
                throws E;
        
        
        
        
}
