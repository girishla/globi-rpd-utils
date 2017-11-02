
package com.globi.rpd;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public class BaseTraverser implements Traverser {

	@Override
	public void traverse(PresentationCatalog presCatalog, Operator<?> anOperator)  {

	}

	@Override
	public void traverse(PresentationTable presTable, Operator<?> anOperator)  {

	}

	@Override
	public void traverse(PresentationColumn presColumn, Operator<?> anOperator)  {

		// nowhere to go from here.
	}

	@Override
	public void traverse(BusinessModel model, Operator<?> anOperator) {
		
		for (LogicalTable table : model.getLogicalTables()) {
			table.apply(anOperator);
		}
		
	}

	@Override
	public void traverse(LogicalTable logicalTable, Operator<?> anOperator) {
		for (LogicalColumn column : logicalTable.getLogicalColumns()) {
			column.apply(anOperator);
		}
		
	}

	@Override
	public void traverse(LogicalColumn logicalColumn, Operator<?> anOperator) {
		// nowhere to go from here.
		
	}

}
