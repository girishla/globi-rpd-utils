package com.globi.rpd.operator;

import java.util.UUID;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.dsl.StandardRpd;
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

		log.debug("*****************************************************************************");
		log.debug("*****************************************************************************");
		log.debug("*****************************************************************************");
		log.debug("*****************************************************************************");
		log.debug("*****************************************************************************");
		log.debug("*****************************************************************************");
		log.debug("Going to delete : " + rpd.getCatalogObjects()
				.size() + " Catalog Objects");

		for (PresentationCatalog catalog : rpd.getCatalogObjects()) {

			DeletingOperator deletingOperator = new DeletingOperator();
			DepthFirstTraversingOperator traverseDeleteOperator = new DepthFirstTraversingOperator(
					new DefaultTraverser(), deletingOperator);
			traverseDeleteOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
			catalog.apply(traverseDeleteOperator);

		}

		rpd.getCatalogObjects()
				.clear();

		for (BusinessModel model : rpd.getModelObjects()) {

			log.debug("Table object Count: " + model.getLogicalTables()
					.size());

			for (LogicalTable table : model.getLogicalTables()) {

				/**
				 * Generation applies only for fact tables
				 */
				if (table.isFactTable()) {

					log.debug("Generating Subject Area for Table: " + table.getName());

					PresentationCatalog catalog = getCatalogFrom(table);

					for (LogicalTable dimTable : table.getJoinedToDimensions()) {

						getPresentationTableFrom(catalog, dimTable);

					}

					getPresentationTableFrom(catalog, table);
					
					/*
					 * for (LogicalColumnW column : table.getXudmlObject()
					 * .getLogicalColumn()) {
					 * 
					 * String newPresColId = "m" + UUID.randomUUID()
					 * .toString(); PresentationColumnW xudmlPresColObject = new
					 * PresentationColumnW();
					 * xudmlPresColObject.setDescription("");
					 * xudmlPresColObject.setHasDispName(false);
					 * 
					 * // TODO add remaining col values
					 * 
					 * PresentationColumn presColumn = new
					 * PresentationColumn(xudmlPresColObject);
					 * presColumn.setId(newPresColId);
					 * 
					 * }
					 */

					rpd.getCatalogObjects()
							.add(catalog);

				}

			}
		}

		return rpd;

	}

	private PresentationCatalog getCatalogFrom(LogicalTable table) {

		String newcatalogId = UUID.randomUUID()
				.toString();
		PresentationCatalog catalog = new PresentationCatalog(newcatalogId);

		PresentationCatalogW xudmlObject = new PresentationCatalogW();
		xudmlObject.setMdsid("m" + newcatalogId);
		xudmlObject.setName("Autogen - " + table.getName());
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

	private PresentationTable getPresentationTableFrom(PresentationCatalog catalog, LogicalTable table) {

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

}
