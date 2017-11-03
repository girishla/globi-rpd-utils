package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;

/**
 * Operator to Resolve Logical joins between Fact Tables and Dimensions.
 * Requires the model object to be Hydrated with all the logical tables and logical joins prior to invocation
 * 
 * @author Girish Lakshmanan
 *
 */
public class ResolveLogicalJoinsOperator implements Operator<BusinessModel> {
	

	@Override
	public BusinessModel operate(BusinessModel model) {
	
		if(model.getXudmlObject()==null)
			throw new IllegalStateException("ResolveJoinsOperator: Cannot resolve joins without a XUDML instance set");
		
		// Unmarshall Logical Joins
			

		
		return null;
		
	}
	

}
