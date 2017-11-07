
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
public class DepthFirstTraversingOperator implements Operator<RpdComponent>{

	private boolean traverseFirst = false;
	private Operator<RpdComponent> operator;
	private Traverser traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public DepthFirstTraversingOperator(Traverser aTraverser, Operator<RpdComponent> anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		traverser.traverse((PresentationCatalog) presCatalog, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), presCatalog);
		}
		presCatalog =  (PresentationCatalog)presCatalog.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), presCatalog);
		}
		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		
		traverser.traverse((PresentationTable) presTable, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), presTable);
		}
		presTable = (PresentationTable) presTable.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), presTable);
		}

		return presTable;
	}

	@Override
	public PresentationColumn operate(PresentationColumn presColumn) {


		traverser.traverse((PresentationColumn) presColumn, this);
		if (progressMonitor != null) {
			// progressMonitor.traversed(traverser.getClass().getName(),
			// presColumn);
		}

		presColumn = presColumn.apply(operator);
		if (progressMonitor != null) {
			// progressMonitor.operated(operator.getClass().getName(),
			// presColumn);
		}

		return presColumn;
	}
	
	
	@Override
	public BusinessModel operate(BusinessModel model) {

		traverser.traverse((BusinessModel) model, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		
		model = (BusinessModel) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}

		return model;
	}
	
	
	@Override
	public LogicalTable operate(LogicalTable model) {


		traverser.traverse((LogicalTable) model, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		
		model = (LogicalTable) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}
		return model;
	}
	
	
	@Override
	public PresentationHierarchy operate(PresentationHierarchy model) {


		traverser.traverse((PresentationHierarchy) model, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		
		model = (PresentationHierarchy) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}
		return model;
	}
	
}
