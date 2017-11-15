
package com.globi.rpd.traverser;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
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
import com.globi.rpd.component.StandardRpd;
import com.globi.rpd.operator.InputOperator;
import com.globi.rpd.operator.Operator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TraverserWithInput implements InputTraverser {

	@Override
	public void traverse(StandardRpd rpd, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {

		for (Database db : rpd.getPhysicalObjects()) {
			db.applyWithInput(anOperator, dto);
			log.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			log.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			log.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			log.debug("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			log.debug(db.getName());

		}

		for (BusinessModel model : rpd.getModelObjects())
			model.applyWithInput(anOperator, dto);

		for (PresentationCatalog catalog : rpd.getCatalogObjects())
			catalog.applyWithInput(anOperator, dto);

	}

	@Override
	public void traverse(PresentationCatalog presCatalog, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {

		for (PresentationTable presTable : presCatalog.getPresentationTables()) {

			presTable.applyWithInput(anOperator, dto);
		}
	}

	@Override
	public void traverse(PresentationTable presTable, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {

		for (PresentationColumn presColumn : presTable.getPresentationColumns()) {
			presColumn.applyWithInput(anOperator, dto);
		}

		for (PresentationHierarchy presHierarchy : presTable.getPresentationHierarchies()) {
			presHierarchy.applyWithInput(anOperator, dto);
		}

	}

	@Override
	public void traverse(BusinessModel model, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {
		for (LogicalTable table : model.getLogicalTables()) {
			table.applyWithInput(anOperator, dto);
		}
	}

	@Override
	public void traverse(LogicalTable logicalTable, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {
		for (LogicalColumn column : logicalTable.getLogicalColumns()) {
			column.applyWithInput(anOperator, dto);
		}

	}

	@Override
	public void traverse(PresentationHierarchy presHierarchy, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {
		for (PresentationLevel level : presHierarchy.getPresentationLevels()) {
			level.applyWithInput(anOperator, dto);
		}

	}

	@Override
	public void traverse(Database db, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {
		for (ConnectionPool cp : db.getConnectionPools())
			cp.applyWithInput(anOperator, dto);

		for (Schema schema : db.getSchemas()) {
			schema.applyWithInput(anOperator, dto);
			log.debug("" + schema.getPhysicalTables().size());
		}
	}

	@Override
	public void traverse(PhysicalTable table, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {
		for (PhysicalColumn col : table.getPhysicalColumns())
			col.applyWithInput(anOperator, dto);

		for (PhysicalKey key : table.getPhysicalKeys())
			key.applyWithInput(anOperator, dto);

		for (PhysicalForeignKey key : table.getPhysicalForeignKeys())
			key.applyWithInput(anOperator, dto);

	}

	@Override
	public void traverse(Schema schema, InputOperator<? extends RpdComponent> anOperator,
			List<TableColumnMetadataDTO> dto) {

		// if(schema==null){
		// log.debug("No Schema provided so not traversing");
		// return;
		// }

		log.debug("" + schema.getPhysicalTables().size());
		
		for (PhysicalTable table : schema.getPhysicalTables())
			table.applyWithInput(anOperator, dto);

	}

}
