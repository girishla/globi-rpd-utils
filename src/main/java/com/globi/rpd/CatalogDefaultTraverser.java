
package com.globi.rpd;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public class CatalogDefaultTraverser implements Traverser {

	@Override
	public void traverse(PresentationCatalog presCatalog, Operator anOperator) {
		for (PresentationTable presTable : presCatalog.getPresentationTables()) {
			presTable.apply(anOperator);
		}
	}

	@Override
	public void traverse(PresentationTable presTable, Operator anOperator) {

		for (PresentationColumn presColumn : presTable.getPresentationColumns()) {
			presColumn.apply(anOperator);
		}

	}

	@Override
	public void traverse(BusinessModel model, Operator anOperator) {

		for (LogicalTable table : model.getLogicalTables()) {
			table.apply(anOperator);
		}

	}

	@Override
	public void traverse(LogicalTable logicalTable, Operator anOperator) {
		for (LogicalColumn column : logicalTable.getLogicalColumns()) {
			column.apply(anOperator);
		}

	}

}
