
package com.globi.rpd;


import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public interface Traverser<E extends Throwable >{

    void traverse(PresentationCatalog aBean, Operator<?, E> presCatalog)
            throws E;

        void traverse(PresentationTable aBean, Operator<?, E> presTable)
            throws E;
        
        void traverse(PresentationColumn aBean, Operator<?, E> presColumn)
                throws E;
        
        
}
