
package com.globi.rpd;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationColumn;
import com.globi.rpd.presentationcatalog.PresentationTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TraversingOperator<R, E extends Throwable> implements Operator<R, E> {

	private boolean traverseFirst = false;
	private Operator<R, E> operator;
	private Traverser<E> traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public TraversingOperator(Traverser<E> aTraverser, Operator<R, E> anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public R operate(PresentationCatalog presCatalog) throws E {
		R returnVal;
		returnVal = presCatalog.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.visited(presCatalog);
		}
		traverser.traverse((PresentationCatalog) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(presCatalog);
		}
		return returnVal;
	}

	@Override
	public R operate(PresentationTable presTable) throws E {

		R returnVal;
		returnVal = presTable.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.visited(presTable);
		}
		traverser.traverse((PresentationTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(presTable);
		}
		return returnVal;
	}

	@Override
	public R operate(PresentationColumn presColumn) throws E {

		R returnVal;
		returnVal = presColumn.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.visited(presColumn);
		}
		traverser.traverse((PresentationColumn) returnVal, this);
			if (progressMonitor != null) {
				progressMonitor.traversed(presColumn);
			}
		return returnVal;
	}

}
