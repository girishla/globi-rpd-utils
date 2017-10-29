
package com.globi;


public interface Visitable {


    <R, E extends Throwable >R accept(Operator<R, E> anOperator)
        throws E
    ;

}
