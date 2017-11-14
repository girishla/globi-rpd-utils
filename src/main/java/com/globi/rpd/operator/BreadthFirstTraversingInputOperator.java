
package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.ConnectionPool;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalForeignKey;
import com.globi.rpd.component.PhysicalKey;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
import com.globi.rpd.traverser.Traverser;
import com.globi.rpd.traverser.TraversingOperatorProgressMonitor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BreadthFirstTraversingInputOperator implements Operator<RpdComponent> {

	private boolean traverseFirst = false;
	private Operator<RpdComponent> operator;
	private Traverser traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public BreadthFirstTraversingInputOperator(Traverser aTraverser, Operator<RpdComponent> anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {
		PresentationCatalog returnVal;
		returnVal = (PresentationCatalog) presCatalog.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presCatalog);
		}
		traverser.traverse((PresentationCatalog) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presCatalog);
		}
		return returnVal;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		PresentationTable returnVal;
		returnVal = (PresentationTable) presTable.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presTable);
		}
		traverser.traverse((PresentationTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presTable);
		}
		return returnVal;
	}

	@Override
	public PresentationColumn operate(PresentationColumn presColumn) {

		PresentationColumn returnVal;
		returnVal = presColumn.apply(operator);
		if (progressMonitor != null) {
			// progressMonitor.operated(operator.getClass().getName(),
			// presColumn);
		}
		traverser.traverse((PresentationColumn) returnVal, this);
		if (progressMonitor != null) {
			// progressMonitor.traversed(traverser.getClass().getName(),
			// presColumn);
		}
		return returnVal;
	}

	@Override
	public BusinessModel operate(BusinessModel model) {

		BusinessModel returnVal;
		returnVal = (BusinessModel) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), model);
		}
		traverser.traverse((BusinessModel) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), model);
		}
		return returnVal;
	}

	@Override
	public LogicalTable operate(LogicalTable table) {

		LogicalTable returnVal;
		returnVal = (LogicalTable) table.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), table);
		}
		traverser.traverse((LogicalTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), table);
		}
		return returnVal;
	}

	@Override
	public PresentationHierarchy operate(PresentationHierarchy presHierarchy) {

		PresentationHierarchy returnVal;
		returnVal = (PresentationHierarchy) presHierarchy.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), presHierarchy);
		}
		traverser.traverse((PresentationHierarchy) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), presHierarchy);
		}
		return returnVal;
	}
	
	
	@Override
	public Database operate(Database db) {

		Database returnVal;
		returnVal = (Database) db.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), db);
		}
		traverser.traverse((Database) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), db);
		}
		return returnVal;
	}
	
	
	@Override
	public ConnectionPool operate(ConnectionPool cp) {

		ConnectionPool returnVal;
		returnVal = (ConnectionPool) cp.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), cp);
		}
		traverser.traverse((ConnectionPool) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), cp);
		}
		return returnVal;
	}
	
	@Override
	public Schema operate(Schema schema) {

		Schema returnVal;
		returnVal = (Schema) schema.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), schema);
		}
		traverser.traverse((Schema) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), schema);
		}
		return returnVal;
	}

	@Override
	public PhysicalTable operate(PhysicalTable table) {

		PhysicalTable returnVal;
		returnVal = (PhysicalTable) table.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), table);
		}
		traverser.traverse((PhysicalTable) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), table);
		}
		return returnVal;
	}

	@Override
	public PhysicalColumn operate(PhysicalColumn col) {

		PhysicalColumn returnVal;
		returnVal = (PhysicalColumn) col.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), col);
		}
		traverser.traverse((PhysicalColumn) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), col);
		}
		return returnVal;
	}

	@Override
	public PhysicalKey operate(PhysicalKey key) {

		PhysicalKey returnVal;
		returnVal = (PhysicalKey) key.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), key);
		}
		traverser.traverse((PhysicalKey) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), key);
		}
		return returnVal;
	}


	@Override
	public PhysicalForeignKey operate(PhysicalForeignKey key) {

		PhysicalForeignKey returnVal;
		returnVal = (PhysicalForeignKey) key.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), key);
		}
		traverser.traverse((PhysicalForeignKey) returnVal, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), key);
		}
		return returnVal;
	}

	
	
	
}
