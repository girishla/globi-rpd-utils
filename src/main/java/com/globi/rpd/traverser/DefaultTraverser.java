
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationLevel;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.operator.Operator;


public class DefaultTraverser implements Traverser {

	@Override
	public void traverse(PresentationCatalog presCatalog, Operator<? extends RpdComponent> anOperator) {

		for (PresentationTable presTable : presCatalog.getPresentationTables()) {

			presTable.apply(anOperator);
		}
	}

	@Override
	public void traverse(PresentationTable presTable, Operator<? extends RpdComponent> anOperator) {

		for (PresentationColumn presColumn : presTable.getPresentationColumns()) {
			presColumn.apply(anOperator);
		}
		
		for (PresentationHierarchy presHierarchy : presTable.getPresentationHierarchies()) {
			presHierarchy.apply(anOperator);
		}

	}

	@Override
	public void traverse(BusinessModel model, Operator<? extends RpdComponent> anOperator) {

		for (LogicalTable table : model.getLogicalTables()) {
			table.apply(anOperator);
		}

	}

	@Override
	public void traverse(LogicalTable logicalTable, Operator<? extends RpdComponent> anOperator) {
		for (LogicalColumn column : logicalTable.getLogicalColumns()) {
			column.apply(anOperator);
		}

	}

	@Override
	public void traverse(PresentationHierarchy presHierarchy, Operator<? extends RpdComponent> anOperator) {
		for (PresentationLevel level : presHierarchy.getPresentationLevels()) {
			level.apply(anOperator);
		}

	}

}
