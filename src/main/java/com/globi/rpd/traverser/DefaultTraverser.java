
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.operator.Operator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	}
	
	@Override
	public void traverse(BusinessModel model, Operator<? extends RpdComponent> anOperator) {

		log.info(model.getName());
//		log.info(model.getLogicalTables().size() + "");
		
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

}
