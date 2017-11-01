
package com.globi.rpd;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public class CatalogDefaultTraverser implements Traverser {

	@Override
	public void traverse(PresentationCatalog presCatalog, Operator<?> anOperator)  {
		for (PresentationTable presTable : presCatalog.getPresentationTables()) {
			presTable.apply(anOperator);
		}
	}

	@Override
	public void traverse(PresentationTable presTable, Operator<?> anOperator)  {

		for (PresentationColumn presColumn : presTable.getPresentationColumns()) {
			presColumn.apply(anOperator);
		}

	}

	@Override
	public void traverse(PresentationColumn presColumn, Operator<?> anOperator)  {

		// nowhere to go from here.

	}

}
