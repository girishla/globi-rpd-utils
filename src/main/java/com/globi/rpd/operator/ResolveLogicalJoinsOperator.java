package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;

import lombok.extern.slf4j.Slf4j;

/**
 * Operator to Resolve Logical joins between Fact Tables and Dimensions.
 * Requires the model object to be Hydrated with all the logical tables and
 * logical joins prior to invocation Applies only to the business model so tree
 * traversal is not required
 * 
 * @author Girish Lakshmanan
 *
 */
@Slf4j
public class ResolveLogicalJoinsOperator implements Operator<BusinessModel> {

	/**
	 * populates joinedFrom and joinedTo on relevant LogicalTable object based
	 * on already available LogicalComplexJoin objects in the model
	 */
	@Override
	public BusinessModel operate(BusinessModel model) {

		if (model.getXudmlObject() == null)
			throw new IllegalStateException("ResolveJoinsOperator: Cannot resolve joins without a XUDML instance set");

		for (LogicalTable table : model.getLogicalTables()) {

			for (LogicalComplexJoin join : model.getLogicalComplexJoins()) {
				
				String table1Id = join.getXudmlObject()
						.getLogicalTable1Ref()
						.split("#m")[1];
				String table2Id = join.getXudmlObject()
						.getLogicalTable2Ref()
						.split("#m")[1];

				// find table 1 and 2 in the model
				LogicalTable table1 = model.getLogicalTables()
						.stream()
						.filter(t -> t.getId()
								.equals(table1Id))
						.findFirst()
						.get();

				LogicalTable table2 = model.getLogicalTables()
						.stream()
						.filter(t -> t.getId()
								.equals(table2Id))
						.findFirst()
						.get();

				/**
				 * if table1 has dimension cardinality(0..1) and Table2's id is same
				 * as current logical table, add it to the joinedToDimensions
				 * List of the current logicaltable
				 */
				if ((table2Id.equals(table.getId())) && (join.getXudmlObject()
						.getMultiplicity1()
						.equals("0..1"))) {

					table.getJoinedToDimensions()
							.add(table1);


					log.debug("******************Adding join:  "+table.getName() + "--->" + table1.getName());



				}

				/**
				 * if table2 has dimension cardinality(0..1) and Table1's id is same
				 * as current logical table, add it to the joinedToDimensions
				 * List of the current logicaltable
				 */
				if ((table1Id.equals(table.getId())) && (join.getXudmlObject()
						.getMultiplicity2()
						.equals("0..1"))) {
					table.getJoinedToDimensions()
							.add(table2);
					
					
					log.debug("******************Adding join:  "+table.getName() + "--->" + table2.getName());

					

				}
				
				
				
				
				
				
				
				
				/**
				 * if table1 has fact cardinality(0..n) and Table2's id is same
				 * as current logical table, add it to the joinedFromFacts
				 * List of the current logicaltable
				 */
				if ((table2Id.equals(table.getId())) && (join.getXudmlObject()
						.getMultiplicity1()
						.equals("0..n") || join.getXudmlObject()
						.getMultiplicity1()
						.equals("n..n"))) {

					table.getJoinedFromFacts()
							.add(table1);


					log.debug("******************Adding join " + table.getName() + "<---" + table1.getName());


				}

				/**
				 * if table2 has fact cardinality(0..n) and Table1's id is same
				 * as current logical table, add it to the joinedToDimensions
				 * List of the current logicaltable
				 */
				if ((table1Id.equals(table.getId())) && (join.getXudmlObject()
						.getMultiplicity2()
						.equals("0..n") || join.getXudmlObject()
						.getMultiplicity1()
						.equals("n..n"))) {
					table.getJoinedFromFacts()
							.add(table2);
					
					
					log.debug("******************Adding join " + table.getName() + "<---" + table2.getName());

					

				}
				
				
				
				
				

			}

		}

		return model;

	}

}
