
package com.globi.rpd.operator;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.component.RpdComponent;

/**
 * An object on which Operators can perform Actions
 * @author Girish lakshmanan
 *
 */
public interface Operable<R extends RpdComponent> {


    R apply(Operator<? extends RpdComponent> anOperator);
    
    default public R applyWithInput(InputOperator<? extends RpdComponent> anOperator,TableColumnMetadataDTO dto){
    	
    	return null;
    }


}
