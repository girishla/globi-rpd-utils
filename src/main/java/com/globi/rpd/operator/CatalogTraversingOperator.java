
package com.globi.rpd.operator;

import com.globi.rpd.Operator;
import com.globi.rpd.Traverser;
import com.globi.rpd.TraversingOperatorProgressMonitor;
import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogTraversingOperator<R, E extends Throwable> extends BaseOperator<R,E> {

	private boolean traverseFirst = false;
	private Operator<R, E> operator;
	private Traverser<E> traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public CatalogTraversingOperator(Traverser<E> aTraverser, Operator<R, E> anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public R operate(PresentationCatalog presCatalog) throws E {
		R returnVal;
		returnVal = presCatalog.apply(operator);
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
	public R operate(PresentationTable presTable) throws E {

		R returnVal;
		returnVal = presTable.apply(operator);

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
	public R operate(PresentationColumn presColumn) throws E {

		R returnVal;
		returnVal = presColumn.apply(operator);
		if (progressMonitor != null) {
//			progressMonitor.operated(operator.getClass().getName(), presColumn);
		}
		traverser.traverse((PresentationColumn) returnVal, this);
			if (progressMonitor != null) {
//				progressMonitor.traversed(traverser.getClass().getName(), presColumn);
			}
		return returnVal;
	}

}
