
package com.globi.rpd.operator;

import com.globi.rpd.Operator;
import com.globi.rpd.Traverser;
import com.globi.rpd.TraversingOperatorProgressMonitor;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogTraversingOperator implements Operator{

	private boolean traverseFirst = false;
	private Operator operator;
	private Traverser traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public CatalogTraversingOperator(Traverser aTraverser, Operator anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {
		PresentationCatalog returnVal;
		returnVal = (PresentationCatalog) presCatalog.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), presCatalog);
		}
		traverser.traverse((PresentationCatalog) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), presCatalog);
		}
		return returnVal;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		PresentationTable returnVal;
		returnVal = (PresentationTable) presTable.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), presTable);
		}
		traverser.traverse((PresentationTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), presTable);
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

}
