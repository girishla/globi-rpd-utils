
package com.globi.rpd;

/**
 * An object on which Operators can perform Actions
 * @author LAKSHMG4
 *
 */
public interface Operable {


    <R, E extends Throwable >R apply(Operator<R, E> anOperator)
        throws E
    ;

}
