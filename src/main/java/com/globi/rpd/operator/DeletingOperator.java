package com.globi.rpd.operator;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jline.utils.Log;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.ConnectionPool;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;

public class DeletingOperator implements Operator<RpdComponent> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		if (presCatalog.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		
		presCatalog.getPresentationTables().clear();
		DeletingOperator.deleteFile(presCatalog.getResourceUri());
		
		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		if (presTable.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		presTable.getPresentationColumns().clear();
		DeletingOperator.deleteFile(presTable.getResourceUri());

		return presTable;

	}

	@Override
	public BusinessModel operate(BusinessModel model) {

		if (model.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		model.getLogicalComplexJoins().clear();
		model.getLogicalTables().clear();
		DeletingOperator.deleteFile(model.getResourceUri());
//		model.setXudmlObject(null);

		return model;

	}

	@Override
	public LogicalTable operate(LogicalTable table) {

		if (table.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		
		table.getJoinedFromFacts().clear();
		table.getJoinedToDimensions().clear();
		table.getLogicalColumns().clear();
		DeletingOperator.deleteFile(table.getResourceUri());
//		table.setXudmlObject(null);
		

		return table;

	}
	
	
	@Override
	public PresentationHierarchy operate(PresentationHierarchy table) {

		if (table.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");
		
		table.getPresentationLevels().clear();
		DeletingOperator.deleteFile(table.getResourceUri());
//		table.setXudmlObject(null);
		

		return table;

	}
	
	
	
	@Override
	public LogicalComplexJoin operate(LogicalComplexJoin join) {

		if (join.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		DeletingOperator.deleteFile(join.getResourceUri());
//		join.setXudmlObject(null);
		
		return join;
	}
	
	
	@Override
	public Database operate(Database db) {

		if (db.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		db.getConnectionPools().clear();
		db.getSchemas().clear();
		
		Log.info("DELETING FILE "+db.getResourceUri());
		
		DeletingOperator.deleteFile(db.getResourceUri());

		return db;

	}
	
	
	@Override
	public ConnectionPool operate(ConnectionPool pool) {

		if (pool.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");
		
		DeletingOperator.deleteFile(pool.getResourceUri());

		return pool;

	}
	
	@Override
	public Schema operate(Schema schema) {

		if (schema.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		schema.getPhysicalTables().clear();
		
		
		DeletingOperator.deleteFile(schema.getResourceUri());

		return schema;

	}

	
	@Override
	public PhysicalTable operate(PhysicalTable table) {

		if (table.getXudmlObject() == null)
			throw new IllegalStateException("Cannot delete without a XUDML instance set");

		table.getPhysicalColumns().clear();
		table.getPhysicalForeignKeys().clear();
		table.getPhysicalKeys().clear();
		
		DeletingOperator.deleteFile(table.getResourceUri());

		return table;

	}
	
	
	@Override
	public PhysicalColumn operate(PhysicalColumn col) {

		//dp nothing
		return col;

	}
	
	
	
	private static void deleteFile(String resourceUri){
		Path p = Paths.get(resourceUri.replace("file:", ""));
		p.toFile().delete();
		
	}
	

}
