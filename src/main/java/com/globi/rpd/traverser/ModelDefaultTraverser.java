
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.operator.Operator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModelDefaultTraverser implements Traverser {
	

	@Override
	public void traverse(BusinessModel model, Operator anOperator) {

		log.info(model.getName());
//		log.info(model.getLogicalTables().size() + "");
		
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
