package com.globi.rpd.operator;

import org.jline.utils.Log;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
import com.globi.rpd.xudml.XudmlMarshaller;

import lombok.extern.slf4j.Slf4j;
import xudml.BusinessModelW;
import xudml.DatabaseW;
import xudml.LogicalComplexJoinW;
import xudml.LogicalTableW;
import xudml.ObjectFactory;
import xudml.PhysicalTableW;
import xudml.PresentationCatalogW;
import xudml.SchemaW;

@Slf4j
public class XudmlMarshallingOperator implements Operator<RpdComponent> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		if(presCatalog.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(presCatalog.getResourceUri(),factory.createPresentationCatalog(presCatalog.getXudmlObject()));

		
		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {
	
		if(presTable.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(presTable.getResourceUri(),factory.createPresentationTable(presTable.getXudmlObject()));
		
		
		return presTable;
		
	}
	
	
	
	@Override
	public BusinessModel operate(BusinessModel model) {
	
		if(model.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<BusinessModelW> marshaller = new XudmlMarshaller<BusinessModelW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(model.getResourceUri(),factory.createBusinessModel(model.getXudmlObject()));
		
		return model;
		
	}
	
	
	
	@Override
	public LogicalTable operate(LogicalTable table) {
	
		if(table.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<LogicalTableW> marshaller = new XudmlMarshaller<LogicalTableW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(table.getResourceUri(),factory.createLogicalTable(table.getXudmlObject()));
		
		return table;
		
	}
	
	
	@Override
	public LogicalComplexJoin operate(LogicalComplexJoin join) {
	
		if(join.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<LogicalComplexJoinW> marshaller = new XudmlMarshaller<LogicalComplexJoinW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(join.getResourceUri(),factory.createLogicalComplexJoin(join.getXudmlObject()));
		
		return join;
		
	}
	
	
	@Override
	public Database operate(Database db) {
	
		if(db.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<DatabaseW> marshaller = new XudmlMarshaller<DatabaseW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(db.getResourceUri(),factory.createDatabase(db.getXudmlObject()));
		
		return db;
		
	}
	
	
	@Override
	public Schema operate(Schema schema) {
	
		if(schema.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<SchemaW> marshaller = new XudmlMarshaller<SchemaW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(schema.getResourceUri(),factory.createSchema(schema.getXudmlObject()));
		
		return schema;
		
	}
	
	
	@Override
	public PhysicalTable operate(PhysicalTable table) {
	
		if(table.getXudmlObject()==null)
			throw new IllegalStateException("Cannot marshall withour a XUDML instance set");
		
		XudmlMarshaller<PhysicalTableW> marshaller = new XudmlMarshaller<PhysicalTableW>();
		ObjectFactory factory = new ObjectFactory();
		marshaller.marshall(table.getResourceUri(),factory.createPhysicalTable(table.getXudmlObject()));
		
		log.debug("££££££££££££££££££££££££££££££" + table.getXudmlObject().getName());
		for(PhysicalColumn c:table.getPhysicalColumns()){
			c.getXudmlObject().setDescription("aaaaaaaaaaaaaaaaa");
			log.debug(c.getName());
			log.debug(c.getXudmlObject().getMdsid());
			log.debug(c.getXudmlObject().getPrecision() + "");
		}
		
		
		return table;
		
	}
	

}
