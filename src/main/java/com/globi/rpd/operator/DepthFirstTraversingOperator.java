
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
public class DepthFirstTraversingOperator implements Operator<RpdComponent>{

	private boolean traverseFirst = false;
	private Operator<RpdComponent> operator;
	private Traverser traverser;
	private TraversingOperatorProgressMonitor progressMonitor;

	public DepthFirstTraversingOperator(Traverser aTraverser, Operator<RpdComponent> anOperator) {
		traverser = aTraverser;
		operator = anOperator;
	}

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		traverser.traverse((PresentationCatalog) presCatalog, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), presCatalog);
		}
		presCatalog =  (PresentationCatalog)presCatalog.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), presCatalog);
		}
		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		
		traverser.traverse((PresentationTable) presTable, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), presTable);
		}
		presTable = (PresentationTable) presTable.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), presTable);
		}

		return presTable;
	}

	@Override
	public PresentationColumn operate(PresentationColumn presColumn) {


		traverser.traverse((PresentationColumn) presColumn, this);
		if (progressMonitor != null) {
			// progressMonitor.traversed(traverser.getClass().getName(),
			// presColumn);
		}

		presColumn = presColumn.apply(operator);
		if (progressMonitor != null) {
			// progressMonitor.operated(operator.getClass().getName(),
			// presColumn);
		}

		return presColumn;
	}
	
	
	@Override
	public BusinessModel operate(BusinessModel model) {

		traverser.traverse((BusinessModel) model, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		
		model = (BusinessModel) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}

		return model;
	}
	
	
	@Override
	public LogicalTable operate(LogicalTable model) {


		traverser.traverse((LogicalTable) model, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		
		model = (LogicalTable) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}
		return model;
	}
	
	
	@Override
	public PresentationHierarchy operate(PresentationHierarchy model) {


		traverser.traverse((PresentationHierarchy) model, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass().getName(), model);
		}
		
		model = (PresentationHierarchy) model.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass().getName(), model);
		}
		return model;
	}
	
	
	
	@Override
	public Database operate(Database db) {

	
		traverser.traverse((Database) db, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), db);
		}

		db = (Database) db.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), db);
		}

		return db;
	}
	
	
	@Override
	public ConnectionPool operate(ConnectionPool cp) {

		
		traverser.traverse((ConnectionPool) cp, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), cp);
		}
		
		cp = (ConnectionPool) cp.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), cp);
		}

		return cp;
	}
	
	@Override
	public Schema operate(Schema schema) {

		traverser.traverse((Schema) schema, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), schema);
		}

		schema = (Schema) schema.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), schema);
		}

		return schema;
	}

	@Override
	public PhysicalTable operate(PhysicalTable table) {

		traverser.traverse((PhysicalTable) table, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), table);
		}
		
		table = (PhysicalTable) table.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), table);
		}

		return table;
	}

	@Override
	public PhysicalColumn operate(PhysicalColumn col) {


		traverser.traverse((PhysicalColumn) col, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), col);
		}
		
		col = (PhysicalColumn) col.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), col);
		}

		return col;
	}

	@Override
	public PhysicalKey operate(PhysicalKey key) {

		traverser.traverse((PhysicalKey) key, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), key);
		}
		
		key = (PhysicalKey) key.apply(operator);
		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), key);
		}

		return key;
	}


	@Override
	public PhysicalForeignKey operate(PhysicalForeignKey key) {

		traverser.traverse((PhysicalForeignKey) key, this);
		if (progressMonitor != null) {
			progressMonitor.traversed(traverser.getClass()
					.getName(), key);
		}

		key = (PhysicalForeignKey) key.apply(operator);

		if (progressMonitor != null) {
			progressMonitor.operated(operator.getClass()
					.getName(), key);
		}

		return key;
	}

	
	
}
