

package com.globi;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraversingVisitor<R, E extends Throwable >
    implements Visitor<R, E>
{

    private boolean traverseFirst=false;
    private Visitor<R, E> visitor;
    private Traverser<E> traverser;
    private TraversingVisitorProgressMonitor progressMonitor;

    public TraversingVisitor(Traverser<E> aTraverser, Visitor<R, E> aVisitor) {
        traverser = aTraverser;
        visitor = aVisitor;
    }


    
    @Override
    public R visit(PresentationCatalog aBean)
        throws E
    {
//        if (traverseFirst == true) {
//            getTraverser().traverse(aBean, this);
//            if (progressMonitor!= null) {
//                progressMonitor.traversed(aBean);
//            }
//        }
        R returnVal;
        returnVal = aBean.accept(getVisitor());
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
    public R visit(PresentationTable aBean)
        throws E
    {
//        if (traverseFirst == true) {
//            getTraverser().traverse(aBean, this);
//            if (progressMonitor!= null) {
//                progressMonitor.traversed(aBean);
//            }
//        }
        R returnVal;
        returnVal = aBean.accept(getVisitor());
        
        
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
    public R visit(PresentationColumn aBean)
        throws E
    {
//        if (traverseFirst == true) {
//            getTraverser().traverse(aBean, this);
//            if (progressMonitor!= null) {
//                progressMonitor.traversed(aBean);
//            }
//        }
        R returnVal;
        returnVal = aBean.accept(getVisitor());
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
