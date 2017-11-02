package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.xudml.XudmlMarshaller;

import xudml.BusinessModelW;
import xudml.LogicalTableW;
import xudml.ObjectFactory;
import xudml.PresentationCatalogW;

public class XudmlMarshallingOperator implements Operator {

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

}
