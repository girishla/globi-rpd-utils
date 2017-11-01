
package com.globi.rpd;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public class CatalogDefaultTraverser<E extends Throwable> implements Traverser<E> {

	@Override
	public void traverse(PresentationCatalog presCatalog, Operator<?, E> anOperator) throws E {
		for (PresentationTable presTable : presCatalog.getPresentationTables()) {
			presTable.apply(anOperator);
		}

	}

	@Override
	public void traverse(PresentationTable presTable, Operator<?, E> anOperator) throws E {



		for (PresentationColumn presColumn : presTable.getPresentationColumns()) {
			presColumn.apply(anOperator);
		}

	}

	@Override
	public void traverse(PresentationColumn presColumn, Operator<?, E> anOperator) throws E {

		// nowhere to go from here.

	}

}
