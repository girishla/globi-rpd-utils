package com.globi.rpd.operator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
import xudml.AliasW;
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

		for (PresentationCatalog catalog : rpd.getCatalogObjects()) {

			/**
			 * Delete only if NOT pinned
			 */
			if (catalog.getXudmlObject()
					.getIconIndex() != 89) {
				DeletingOperator deletingOperator = new DeletingOperator();
				DepthFirstTraversingOperator traverseDeleteOperator = new DepthFirstTraversingOperator(
						new DefaultTraverser(), deletingOperator);
				traverseDeleteOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
				catalog.apply(traverseDeleteOperator);
			}
		}

		// Make a copy of the ones not deleted before clearing the whole list
		// because we need to reference them in the generation process to
		// identify duplicates etc
		List<PresentationCatalog> catalogListCopy = new ArrayList<>(rpd.getCatalogObjects());
		catalogListCopy = catalogListCopy.stream()
				.filter(c -> c.getXudmlObject()
						.getIconIndex() == 89)
				.collect(Collectors.toList());

		
		
		/**
		 * This is cleared because Catalog is a top level marshalled file and we
		 * only need to keep in memory the ones we are making changes to or
		 * newly added ones
		 */
		rpd.getCatalogObjects()
				.clear();

		for (BusinessModel model : rpd.getModelObjects()) {

			/**
			 * Do nothing if the model is pinned, else proceed
			 */
			if (!(model.getXudmlObject()
					.getIconIndex() == 89)) {

				/**
				 * Add ONE combined Admin subject Area per Model
				 */
				PresentationCatalog combinedCatalog = getCombinedCatalogForModel(model);
				/**
				 * Add an Alias to the Combined Catalog in the Format ModelName - Administration - ModelName
				 */
				AliasW combinedCatalogAlias=new AliasW();
				combinedCatalogAlias.setName(model.getName() +  " - Administration - " + model.getName());
				combinedCatalog.getXudmlObject().getAlias().add(combinedCatalogAlias);
				
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
						 * Add an Alias to the new Catalog in the Format ModelName - FactName
						 */
						AliasW catalogAlias=new AliasW();
						catalogAlias.setName(model.getName() +  " - " + table.getName());
						catalog.getXudmlObject().getAlias().add(catalogAlias);
						
						/**
						 * Add a display name without the LN prefix
						 */
						catalog.getXudmlObject().setHasDispName(true);
						catalog.getXudmlObject().setDispName(table.getName());
					
						
						/**
						 * Add Presentation table and column for Dim tables
						 */
						for (LogicalTable dimTable : table.getJoinedToDimensions()) {

							PresentationTable presTable = updateCatalogAndGetPresentationTableFrom(catalog, dimTable);
							addPresentationColumnsFromLogicalTable(dimTable, presTable);

							/**
							 * Add to combined catalog only if the Dimension
							 * Table does not already have a corresponding
							 * Presentation table
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

						/**
						 * Only add it if Name of newly generated Catalog is NOT
						 * same as another existing catalog ending with the same
						 * Name Note that this may not work in all cases so a
						 * more elegant solution needs to be found. Example: If
						 * there is an existing catalog called "Global Reporting
						 * - Measures - Activity" and the new one is "Activity"
						 * then it will be skipped
						 */
						if (!equivalentCatalogExistsForFactTable(catalogListCopy, catalog))
							rpd.getCatalogObjects()
									.add(catalog);

					}

				}
			}

		}
		return rpd;

	}

	private boolean tableExistsInCatalog(LogicalTable table, PresentationCatalog catalog) {

		return catalog.getPresentationTables()
				.stream()
				.anyMatch(t -> t.getName()
						.equals(table.getName()));

	}

	private boolean equivalentCatalogExistsForFactTable(List<PresentationCatalog> catalogListCopy,
			PresentationCatalog catalog) {

		return catalogListCopy.stream()
				.anyMatch(c -> {
					return c.getName()
							.endsWith(catalog.getName());
				});
	}

	private PresentationCatalog getCombinedCatalogForModel(BusinessModel model) {

		String newcatalogId = UUID.randomUUID()
				.toString();
		PresentationCatalog catalog = new PresentationCatalog(newcatalogId);

		PresentationCatalogW xudmlObject = new PresentationCatalogW();
		xudmlObject.setMdsid("m" + newcatalogId);
		xudmlObject.setName("Administration - " + model.getName());
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
		xudmlObject.setName("LN " + table.getName()
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
