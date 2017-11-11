package com.globi.rpd.operator;

import java.util.UUID;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.StandardRpd;
import com.globi.rpd.traverser.DefaultTraverser;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.extern.slf4j.Slf4j;
import xudml.LogicalColumnW;
import xudml.PresentationCatalogW;
import xudml.PresentationColumnW;
import xudml.PresentationTableW;
import xudml.RefPresentationCatalogTableT;
import xudml.RefTablePresentationCatalogTableT;

/**
 * Generates a Presentation Catalog given a Logical Table; Will only process
 * Logical Tables that are fact tables.
 * 
 * @author Girish Lakshmanan
 *
 */
@Slf4j
public class SubjectAreaGeneratorOperator implements Operator<StandardRpd> {

	@Override
	public StandardRpd operate(StandardRpd rpd) {

		if (!(rpd.getModelObjects()
				.size() > 0)) {
			return rpd;
		}

		log.debug("Going to delete : " + rpd.getCatalogObjects()
				.size() + " Catalog Objects");

		for (PresentationCatalog catalog : rpd.getCatalogObjects()) {

			/**
			 * Delete only if NOT pinned
			 */
			if (!(catalog.getXudmlObject()
					.getIconIndex() == 89)) {
				DeletingOperator deletingOperator = new DeletingOperator();
				DepthFirstTraversingOperator traverseDeleteOperator = new DepthFirstTraversingOperator(
						new DefaultTraverser(), deletingOperator);
				traverseDeleteOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
				catalog.apply(traverseDeleteOperator);
			}
		}

		rpd.getCatalogObjects()
				.clear();

		for (BusinessModel model : rpd.getModelObjects()) {

			/**
			 * Add ONE combined Admin subject Area per Model
			 */
			PresentationCatalog combinedCatalog = getCombinedCatalogForModel(model);
			rpd.getCatalogObjects()
					.add(combinedCatalog);

			for (LogicalTable table : model.getLogicalTables()) {

				/**
				 * Generation applies only for fact tables
				 */
				if (table.isFactTable()) {

					log.debug("Generating Subject Area for Table: " + table.getName());

					PresentationCatalog catalog = getCatalogFrom(table);

					/**
					 * Add Presentation table and column for Dim tables
					 */
					for (LogicalTable dimTable : table.getJoinedToDimensions()) {

						PresentationTable presTable = updateCatalogAndGetPresentationTableFrom(catalog, dimTable);
						addPresentationColumnsFromLogicalTable(dimTable, presTable);

						/**
						 * Add to combined catalog only if the Dimension Table
						 * does not already have a corresponding Presentation
						 * table
						 */
						if (!tableExistsInCatalog(dimTable, combinedCatalog)) {
							PresentationTable presTableForCombinedCatalog = updateCatalogAndGetPresentationTableFrom(
									combinedCatalog, dimTable);
							addPresentationColumnsFromLogicalTable(dimTable, presTableForCombinedCatalog);
						}

					}

					/**
					 * Add Presentation table and column for 1 Fact table
					 */
					PresentationTable presFactTable = updateCatalogAndGetPresentationTableFrom(catalog, table);
					addPresentationColumnsFromLogicalTable(table, presFactTable);
					PresentationTable presFactTableForCombinedCatalog = updateCatalogAndGetPresentationTableFrom(
							combinedCatalog, table);
					addPresentationColumnsFromLogicalTable(table, presFactTableForCombinedCatalog);

					rpd.getCatalogObjects()
							.add(catalog);

				}

			}
		}

		return rpd;

	}

	private boolean tableExistsInCatalog(LogicalTable table, PresentationCatalog catalog) {

		return catalog.getPresentationTables()
				.stream()
				.anyMatch(t -> t.getName()
						.contains(table.getName()));

	}

	private PresentationCatalog getCombinedCatalogForModel(BusinessModel model) {

		String newcatalogId = UUID.randomUUID()
				.toString();
		PresentationCatalog catalog = new PresentationCatalog(newcatalogId);

		PresentationCatalogW xudmlObject = new PresentationCatalogW();
		xudmlObject.setMdsid("m" + newcatalogId);
		xudmlObject.setName("Administration - "+model.getName());
		xudmlObject.setHasDispName(false);
		xudmlObject.setHasDispDescription(false);
		xudmlObject.setIsAutoAggr(false);
		xudmlObject.setSubjectAreaRef(XudmlConstants.XUDML_MODELURL + model.getId() + ".xml#m" + model.getId());
		RefTablePresentationCatalogTableT emptyRefTable = new RefTablePresentationCatalogTableT();
		xudmlObject.setRefTables(emptyRefTable);
		catalog.setXudmlObject(xudmlObject);

		return catalog;

	}

	private PresentationCatalog getCatalogFrom(LogicalTable table) {

		String newcatalogId = UUID.randomUUID()
				.toString();
		PresentationCatalog catalog = new PresentationCatalog(newcatalogId);

		PresentationCatalogW xudmlObject = new PresentationCatalogW();
		xudmlObject.setMdsid("m" + newcatalogId);
		xudmlObject.setName(table.getName()
				.replace("Measures - ", ""));
		xudmlObject.setHasDispName(false);
		xudmlObject.setHasDispDescription(false);
		xudmlObject.setIsAutoAggr(false);
		xudmlObject.setSubjectAreaRef(table.getXudmlObject()
				.getSubjectAreaRef());
		RefTablePresentationCatalogTableT emptyRefTable = new RefTablePresentationCatalogTableT();
		xudmlObject.setRefTables(emptyRefTable);
		catalog.setXudmlObject(xudmlObject);

		return catalog;
	}

	private PresentationTable updateCatalogAndGetPresentationTableFrom(PresentationCatalog catalog,
			LogicalTable table) {

		String id = UUID.randomUUID()
				.toString();
		PresentationTable presTable = new PresentationTable("m" + catalog.getId() + "-m" + id);
		PresentationTableW xudmlObject = new PresentationTableW();
		xudmlObject.setMdsid("m" + id);
		xudmlObject.setName(table.getName());
		xudmlObject.setHasDispName(false);
		xudmlObject.setHasDispDescription(false);
		xudmlObject.setContainerRef(XudmlConstants.XUDML_CATALOGURL + catalog.getId() + ".xml#m" + catalog.getId());
		presTable.setXudmlObject(xudmlObject);
		catalog.getPresentationTables()
				.add(presTable);
		RefPresentationCatalogTableT refTable = new RefPresentationCatalogTableT();
		refTable.setPresentationTableRef(
				XudmlConstants.XUDML_PRESTABLEURL + presTable.getId() + ".xml#m" + presTable.getId());
		refTable.setRefId(presTable.getRefId());
		catalog.getXudmlObject()
				.getRefTables()
				.getRefPresentationTable()
				.add(refTable);

		return presTable;
	}

	private void addPresentationColumnsFromLogicalTable(LogicalTable logicalTable, PresentationTable presTable) {

		for (LogicalColumnW column : logicalTable.getXudmlObject()
				.getLogicalColumn()) {

			String newPresColId = UUID.randomUUID()
					.toString();
			PresentationColumnW xudmlPresColObject = new PresentationColumnW();
			xudmlPresColObject.setDescription("");
			xudmlPresColObject.setHasDispName(false);
			xudmlPresColObject.setMdsid("m" + newPresColId);
			xudmlPresColObject.setOverrideLogicalName(false);
			xudmlPresColObject.setLogicalColumnRef(
					XudmlConstants.XUDML_LOGICALTABLEURL + logicalTable.getId() + ".xml#" + column.getMdsid());
			xudmlPresColObject.setName(column.getName());

			PresentationColumn presColumn = new PresentationColumn(xudmlPresColObject);
			presColumn.setId(newPresColId);
			presTable.getPresentationColumns()
					.add(presColumn);
			presTable.getXudmlObject()
					.getPresentationColumn()
					.add(xudmlPresColObject);

		}

	}

}
