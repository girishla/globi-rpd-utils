
package com.globi;


public interface Visitable {


    <R, E extends Throwable >R accept(Visitor<R, E> aVisitor)
        throws E
    ;

}
