

package com.globi.rpd.traverser;


public interface TraversingOperatorProgressMonitor {

    void operated(String operationName, Object aOperatable);

    void traversed(String operationName,Object aOperatable);

}
