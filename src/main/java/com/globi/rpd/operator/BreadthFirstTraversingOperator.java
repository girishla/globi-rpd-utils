
package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.traverser.Traverser;
import com.globi.rpd.traverser.TraversingOperatorProgressMonitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreadthFirstTraversingOperator implements Operator<RpdComponent> {

	private boolean traverseFirst = false;
	private Operator<RpdComponent> operator;
	private Traverser traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public BreadthFirstTraversingOperator(Traverser aTraverser, Operator<RpdComponent> anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {
		PresentationCatalog returnVal;
		returnVal = (PresentationCatalog) presCatalog.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presCatalog);
		}
		traverser.traverse((PresentationCatalog) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presCatalog);
		}
		return returnVal;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		PresentationTable returnVal;
		returnVal = (PresentationTable) presTable.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presTable);
		}
		traverser.traverse((PresentationTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presTable);
		}
		return returnVal;
	}

	@Override
	public PresentationColumn operate(PresentationColumn presColumn) {

		PresentationColumn returnVal;
		returnVal = presColumn.apply(operator);
		if (progressMonitor != null) {
			// progressMonitor.operated(operator.getClass().getName(),
			// presColumn);
		}
		traverser.traverse((PresentationColumn) returnVal, this);
		if (progressMonitor != null) {
			// progressMonitor.traversed(traverser.getClass().getName(),
			// presColumn);
		}
		return returnVal;
	}

	@Override
	public BusinessModel operate(BusinessModel model) {

		BusinessModel returnVal;
		returnVal = (BusinessModel) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), model);
		}
		traverser.traverse((BusinessModel) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), model);
		}
		return returnVal;
	}

	@Override
	public LogicalTable operate(LogicalTable table) {

		LogicalTable returnVal;
		returnVal = (LogicalTable) table.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), table);
		}
		traverser.traverse((LogicalTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), table);
		}
		return returnVal;
	}

	@Override
	public PresentationHierarchy operate(PresentationHierarchy presHierarchy) {

		PresentationHierarchy returnVal;
		returnVal = (PresentationHierarchy) presHierarchy.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presHierarchy);
		}
		traverser.traverse((PresentationHierarchy) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presHierarchy);
		}
		return returnVal;
	}

}
