package com.globi.rpd.operator;

import java.util.UUID;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;

import xudml.LogicalColumnW;
import xudml.PresentationCatalogW;
import xudml.PresentationColumnW;
import xudml.RefTablePresentationCatalogTableT;

/**
 * Generates a Presentation Catalog given a Logical Table Will only process
 * Logical Tables that are measures.
 * 
 * @author Girish Lakshmanan
 *
 */
public class SubjectAreaGeneratorOperator implements Operator<BusinessModel> {

	@Override
	public BusinessModel operate(BusinessModel model) {

		if (model.getXudmlObject() == null)
			throw new IllegalStateException(
					"SubjectAreaGeneratorOperator: Cannot generate subject area without a XUDML instance set");

		for (LogicalTable table : model.getLogicalTables()) {

			/**
			 * Generation applies only for fact tables
			 */
			if (table.isFactTable()) {

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

				for (LogicalColumnW column : table.getXudmlObject()
						.getLogicalColumn()) {

					String newPresColId = "m" + UUID.randomUUID()
							.toString();
					PresentationColumnW xudmlPresColObject = new PresentationColumnW();
					xudmlPresColObject.setDescription("");
					xudmlPresColObject.setHasDispName(false);

					// TODO add remaining col values

					PresentationColumn presColumn = new PresentationColumn(xudmlPresColObject);
					presColumn.setId(newPresColId);

				}
			}

		}

		return model;

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
