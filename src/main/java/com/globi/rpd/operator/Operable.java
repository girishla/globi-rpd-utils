
package com.globi.rpd.operator;

/**
 * An object on which Operators can perform Actions
 * @author LAKSHMG4
 *
 */
public interface Operable<R> {


    R apply(Operator anOperator);

}
