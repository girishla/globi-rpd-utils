
package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.traverser.Traverser;
import com.globi.rpd.traverser.TraversingOperatorProgressMonitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelTraversingOperator implements Operator{

	private boolean traverseFirst = false;
	private Operator operator;
	private Traverser traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public ModelTraversingOperator(Traverser aTraverser, Operator anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public BusinessModel operate(BusinessModel model) {

		BusinessModel returnVal;
		returnVal = (BusinessModel) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}
		traverser.traverse((BusinessModel) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		return returnVal;
	}
	
	
	@Override
	public LogicalTable operate(LogicalTable model) {

		LogicalTable returnVal;
		returnVal = (LogicalTable) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}
		traverser.traverse((LogicalTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		return returnVal;
	}
	
}
