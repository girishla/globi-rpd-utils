package com.globi.rpd.operator;

import com.globi.rpd.AppProperties;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.xudml.ResourceFactory;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;
import com.globi.rpd.xudml.XudmlMarshaller;

import xudml.BusinessModelW;
import xudml.LogicalComplexJoinW;
import xudml.LogicalTableW;
import xudml.PresentationCatalogW;
import xudml.PresentationTableW;

public class XudmlUnmarshallingOperator implements Operator<RpdComponent> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		XudmlMarshaller<PresentationCatalogW> marshaller = new XudmlMarshaller<PresentationCatalogW>();

		presCatalog.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL(presCatalog.getResourceUri())));

		/**
		 * basic Hydration of children is done here so that the child references
		 * are available during unmarshalling each child Full hydration can only
		 * be done after unmarshalling each child
		 */
		presCatalog.getXudmlObject()//
				.getRefTables()//
				.getRefPresentationTable()//
				.stream()//
				.forEach(table -> {
					presCatalog.getPresentationTables()
							.add(new PresentationTable(table.getRefId()));

				});

		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		XudmlMarshaller<PresentationTableW> marshaller = new XudmlMarshaller<PresentationTableW>();
		presTable.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL("file:" + presTable.getResourceUri())));

		return presTable;

	}

	@Override
	public BusinessModel operate(BusinessModel model) {

		XudmlMarshaller<BusinessModelW> marshaller = new XudmlMarshaller<BusinessModelW>();
		model.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL(model.getResourceUri())));

		/**
		 * basic Hydration of children is done here so that the child references
		 * are available during unmarshalling each child Full hydration can only
		 * be done after unmarshalling each child
		 */
		XudmlFolder folder;

		folder = new XudmlFolder( AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_LOGICALTABLEURL);

		folder.getResources()
				.stream()
				.map(resource -> resource.getFilename())
				.map(name -> name.replace(".xml", ""))
				.forEach(id ->

		{
					model.getLogicalTables()
							.add(new LogicalTable(id));
				});

		return model;

	}

	@Override
	public LogicalTable operate(LogicalTable logicalTable) {

		XudmlMarshaller<LogicalTableW> marshaller = new XudmlMarshaller<LogicalTableW>();
		logicalTable.setXudmlObject(
				marshaller.unmarshall(ResourceFactory.fromURL("file:" + logicalTable.getResourceUri())));
		return logicalTable;

	}

	@Override
	public LogicalComplexJoin operate(LogicalComplexJoin join) {

		XudmlMarshaller<LogicalComplexJoinW> marshaller = new XudmlMarshaller<LogicalComplexJoinW>();
		join.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL("file:" + join.getResourceUri())));
		return join;

	}

}
