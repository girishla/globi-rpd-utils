
package com.globi.rpd.operator;

import com.globi.rpd.component.RpdComponent;

/**
 * An object on which Operators can perform Actions
 * @author LAKSHMG4
 *
 */
public interface Operable<R extends RpdComponent> {


    R apply(Operator<? extends RpdComponent> anOperator);

}
