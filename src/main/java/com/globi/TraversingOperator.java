

package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraversingOperator<R, E extends Throwable >
    implements Operator<R, E>
{

    private boolean traverseFirst=false;
    private Operator<R, E> visitor;
    private Traverser<E> traverser;
    private TraversingOperatorProgressMonitor progressMonitor;

    public TraversingOperator(Traverser<E> aTraverser, Operator<R, E> anOperator) {
        traverser = aTraverser;
        visitor = anOperator;
    }


    
    @Override
    public R operate(PresentationCatalog aBean)
        throws E
    {
//        if (traverseFirst == true) {
//            getTraverser().traverse(aBean, this);
//            if (progressMonitor!= null) {
//                progressMonitor.traversed(aBean);
//            }
//        }
        R returnVal;
        returnVal = aBean.apply(getVisitor());
        if (progressMonitor!= null) {
            progressMonitor.visited(aBean);
        }
        if (traverseFirst == false) {
            getTraverser().traverse((PresentationCatalog)returnVal, this);
            if (progressMonitor!= null) {
                progressMonitor.traversed(aBean);
            }
        }
        return returnVal;
    }

    @Override
    public R operate(PresentationTable aBean)
        throws E
    {
//        if (traverseFirst == true) {
//            getTraverser().traverse(aBean, this);
//            if (progressMonitor!= null) {
//                progressMonitor.traversed(aBean);
//            }
//        }
        R returnVal;
        returnVal = aBean.apply(getVisitor());
        
        
        if (progressMonitor!= null) {
            progressMonitor.visited(aBean);
        }
        if (traverseFirst == false) {
            getTraverser().traverse((PresentationTable)returnVal, this);
            if (progressMonitor!= null) {
                progressMonitor.traversed(aBean);
            }
        }
        return returnVal;
    }
    
    
    @Override
    public R operate(PresentationColumn aBean)
        throws E
    {
//        if (traverseFirst == true) {
//            getTraverser().traverse(aBean, this);
//            if (progressMonitor!= null) {
//                progressMonitor.traversed(aBean);
//            }
//        }
        R returnVal;
        returnVal = aBean.apply(getVisitor());
        if (progressMonitor!= null) {
            progressMonitor.visited(aBean);
        }
        if (traverseFirst == false) {
            getTraverser().traverse((PresentationColumn)returnVal, this);
            if (progressMonitor!= null) {
                progressMonitor.traversed(aBean);
            }
        }
        return returnVal;
    }
    
    
    
    
}
