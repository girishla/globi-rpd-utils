package com.globi.rpd.operator;

import java.util.UUID;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.dsl.StandardRpd;
import com.globi.rpd.traverser.DefaultTraverser;

import lombok.extern.slf4j.Slf4j;
import xudml.PresentationCatalogW;
import xudml.RefTablePresentationCatalogTableT;

/**
 * Generates a Presentation Catalog given a Logical Table Will only process
 * Logical Tables that are measures.
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
		log.debug("Going to delete : " + rpd.getCatalogObjects().size() +" Catalog Objects");
		
		
		for(PresentationCatalog catalog:rpd.getCatalogObjects()){
			
			DeletingOperator deletingOperator = new DeletingOperator();
			DepthFirstTraversingOperator traverseDeleteOperator = new DepthFirstTraversingOperator(new DefaultTraverser(),
					deletingOperator);
			traverseDeleteOperator.setProgressMonitor(new DefaultLoggerProgressMonitor());
			catalog.apply(traverseDeleteOperator);
			
			
		}
		
		rpd.getCatalogObjects().clear();
		
		
		log.debug("Model object Count: " + rpd.getModelObjects().size());
		
		for (BusinessModel model : rpd.getModelObjects()) {

			for (LogicalTable table : model.getLogicalTables()) {

				/**
				 * Generation applies only for fact tables
				 */
				if (table.isFactTable()) {

					log.info("Generating for Table: " + table.getName());
					

					PresentationCatalog catalog = getCatalogFrom(table);

					// find joined dimensions
					// joinedFrom and joinedTo are LogicalTable fields on each
					// LogicalTable pointing to joined dimensions and facts
					// ResolveLogicalJoinsOperator will already have populated
					// joinedFrom and joinedTo on the

					/*
					 * for each joined dim {
					 * 
					 * add dimensions as PresentationTables Loop though logical
					 * columns and add each col as presentation Column }
					 */

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
					
					rpd.getCatalogObjects().add(catalog);

				}

			}
		}

		return rpd;

	}

	private PresentationCatalog getCatalogFrom(LogicalTable table) {

		String newcatalogId = "m" + UUID.randomUUID()
				.toString();
		PresentationCatalog catalog = new PresentationCatalog(newcatalogId);

		PresentationCatalogW xudmlObject = new PresentationCatalogW();
		xudmlObject.setMdsid(newcatalogId);
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

}
