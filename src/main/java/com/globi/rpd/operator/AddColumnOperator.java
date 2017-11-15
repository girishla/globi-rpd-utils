package com.globi.rpd.operator;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
import com.globi.rpd.component.StandardRpd;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.extern.slf4j.Slf4j;
import xudml.PhysicalColumnW;

@Slf4j
public class AddColumnOperator implements InputOperator<RpdComponent> {

	@Override
	public StandardRpd operate(StandardRpd rpd, List<TableColumnMetadataDTO> dto) {

		if (rpd == null)
			throw new IllegalStateException("Cannot un operation AddColumnOperator without a Rpd instance");
		return rpd;

	}

	@Override
	public Database operate(Database db, List<TableColumnMetadataDTO> dto) {

		if (db.getXudmlObject() == null)
			throw new IllegalStateException("Cannot Run operation AddColumnOperator without a XUDML instance set");

		return db;

	}

	@Override
	public Schema operate(Schema schema, List<TableColumnMetadataDTO> dto) {

		if (schema.getXudmlObject() == null)
			throw new IllegalStateException("Cannot Run operation AddColumnOperator without a XUDML instance set");

		return schema;

	}

	@Override
	public PhysicalTable operate(PhysicalTable table, List<TableColumnMetadataDTO> dto) {

		if (table.getXudmlObject() == null)
			throw new IllegalStateException("Cannot Run operation AddColumnOperator without a XUDML instance set");

		/**
		 * Filter only dto columns belonging to current physical Table
		 */
		Stream<TableColumnMetadataDTO> filteredTableColumns = dto.stream()
				.filter(col -> col.getDimTablename()
						.equals(table.getName()));

		if (table.getName()
				.equals(dto.get(0)
						.getDimTablename())) {

			log.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			log.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			log.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			log.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			log.debug(table.getName() + ":::" + dto.get(0)
					.getDimTablename());
			
			table.getPhysicalColumns().get(0).getXudmlObject().setDescription("hasdjkasbhdkasbhdkashk");
			
		}

		/**
		 * Select Columns that dont already exist in Table
		 */
		List<TableColumnMetadataDTO> newColumns = filteredTableColumns.filter(col -> table.getPhysicalColumns()
				.stream()
				.anyMatch(physicalCol -> !col.getColName()
						.equals(physicalCol.getName())))
				.collect(Collectors.toList());

		/**
		 * Insert New Columns into Physical Table
		 */
		for (TableColumnMetadataDTO column : newColumns) {

			PhysicalColumnW newColumnXudml = new PhysicalColumnW();
			newColumnXudml.setDataType("VARCHAR");
			String id = UUID.randomUUID()
					.toString();
			newColumnXudml.setMdsid("m" + id);
			newColumnXudml.setName(column.getColName());
			newColumnXudml.setPrecision(BigInteger.valueOf(column.getColPrecision()));
			newColumnXudml.setRowCount(0.0);
//			newColumnXudml.setNullable(true);
			newColumnXudml.setSpecialType("none");
//			newColumnXudml.setSourceColumnRef(XudmlConstants.XUDML_PHYSICALTABLEURL + table.getId() + ".xml#m" + table.getId());
			PhysicalColumn newColumn = new PhysicalColumn(newColumnXudml);
			table.getXudmlObject().getPhysicalColumn().add(newColumnXudml);
			table.getPhysicalColumns()
					.add(newColumn);

		}
		
		
		Collections.sort(table.getPhysicalColumns(), new Comparator<PhysicalColumn>() {
			public int compare(PhysicalColumn t1, PhysicalColumn t2) {

				return t1.getXudmlObject()
						.getName()
						.compareTo(t2.getXudmlObject()
								.getName());
			}
		});
		
		

		return table;

	}

}
