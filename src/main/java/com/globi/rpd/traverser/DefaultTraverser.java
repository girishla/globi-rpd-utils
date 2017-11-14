
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.ConnectionPool;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalForeignKey;
import com.globi.rpd.component.PhysicalKey;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationLevel;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
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

	@Override
	public void traverse(Database db, Operator<? extends RpdComponent> anOperator) {
		for (ConnectionPool cp : db.getConnectionPools())
			cp.apply(anOperator);

		for (Schema schema : db.getSchemas())
			schema.apply(anOperator);

	}

	@Override
	public void traverse(PhysicalTable table, Operator<? extends RpdComponent> anOperator) {
		for (PhysicalColumn col : table.getPhysicalColumns())
			col.apply(anOperator);
		
		for (PhysicalKey key : table.getPhysicalKeys())
			key.apply(anOperator);
		
		for (PhysicalForeignKey key : table.getPhysicalForeignKeys())
			key.apply(anOperator);
		
	}

	@Override
	public void traverse(Schema schema, Operator<? extends RpdComponent> anOperator) {
		for (PhysicalTable table : schema.getPhysicalTables())
			table.apply(anOperator);

	}

}
