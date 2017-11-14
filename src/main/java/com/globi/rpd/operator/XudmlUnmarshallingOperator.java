package com.globi.rpd.operator;

import java.util.List;
import java.util.stream.Collectors;

import com.globi.rpd.AppProperties;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.ConnectionPool;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
import com.globi.rpd.xudml.ResourceFactory;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;
import com.globi.rpd.xudml.XudmlMarshaller;

import lombok.extern.slf4j.Slf4j;
import xudml.BusinessModelW;
import xudml.DatabaseW;
import xudml.LogicalComplexJoinW;
import xudml.LogicalTableW;
import xudml.PhysicalTableW;
import xudml.PresentationCatalogW;
import xudml.PresentationHierarchyW;
import xudml.PresentationTableW;
import xudml.RefTableDBConnPoolT;
import xudml.RefTablePresentationCatalogTableT;
import xudml.RefTablePresentationHierarchyT;
import xudml.SchemaW;

@Slf4j
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
		RefTablePresentationCatalogTableT refTable = presCatalog.getXudmlObject()//
				.getRefTables();//

		if (refTable != null) {
			refTable.getRefPresentationTable()//
					.stream()//
					.forEach(table -> {
						presCatalog.getPresentationTables()
								.add(new PresentationTable(table.getRefId()));

					});
		}

		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		XudmlMarshaller<PresentationTableW> marshaller = new XudmlMarshaller<PresentationTableW>();
		presTable.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL("file:" + presTable.getResourceUri())));

		RefTablePresentationHierarchyT refHier = presTable.getXudmlObject()
				.getRefHierarchies();

		if (refHier != null) {

			refHier.getRefPresentationHierarchy()
					.stream()
					.forEach(hierarchy -> {
						presTable.getPresentationHierarchies()
								.add(new PresentationHierarchy(hierarchy.getRefId()));
					});
		}

		return presTable;

	}

	@Override
	public PresentationHierarchy operate(PresentationHierarchy presHierarchy) {

		XudmlMarshaller<PresentationHierarchyW> marshaller = new XudmlMarshaller<PresentationHierarchyW>();
		presHierarchy.setXudmlObject(
				marshaller.unmarshall(ResourceFactory.fromURL("file:" + presHierarchy.getResourceUri())));

		return presHierarchy;

	}

	@Override
	public BusinessModel operate(BusinessModel model) {

		XudmlMarshaller<BusinessModelW> marshaller = new XudmlMarshaller<BusinessModelW>();
		model.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL(model.getResourceUri())));

		/**
		 * Note that LogicalTable is unmarshalled here instead of it's own
		 * overloaded method due to weird xudml structure
		 */
		XudmlFolder folder;

		folder = new XudmlFolder(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_LOGICALTABLEURL);

		List<String> idList = folder.getResources()
				.stream()
				.map(resource -> resource.getFilename())
				.map(name -> name.replace(".xml", ""))
				.collect(Collectors.toList());

		for (String id : idList) {

			LogicalTable logicalTable = new LogicalTable(id);

			XudmlMarshaller<LogicalTableW> tableMarshaller = new XudmlMarshaller<LogicalTableW>();
			logicalTable.setXudmlObject(
					tableMarshaller.unmarshall(ResourceFactory.fromURL("file:" + logicalTable.getResourceUri())));

			String modelRef = logicalTable.getXudmlObject()
					.getSubjectAreaRef();

			if (modelRef.contains(model.getId())) {

				model.getLogicalTables()
						.add(logicalTable);

			} else {
				log.debug("Skipped  " + logicalTable.getResourceUri());

			}

		}

		return model;

	}

	@Override
	public LogicalTable operate(LogicalTable logicalTable) {

		/**
		 * Unmarshalling already done along with the Model - because of weird
		 * XUDML structure. Model XUDML has no references to the Children
		 */

		return logicalTable;

	}

	@Override
	public LogicalComplexJoin operate(LogicalComplexJoin join) {
		XudmlMarshaller<LogicalComplexJoinW> marshaller = new XudmlMarshaller<LogicalComplexJoinW>();
		join.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL("file:" + join.getResourceUri())));
		return join;
	}

	@Override
	public Database operate(Database db) {

		XudmlMarshaller<DatabaseW> marshaller = new XudmlMarshaller<DatabaseW>();

		db.setXudmlObject(marshaller.unmarshall(ResourceFactory.fromURL(db.getResourceUri())));

		/**
		 * basic Hydration of children is done here so that the child references
		 * are available during unmarshalling each child Full hydration can only
		 * be done after unmarshalling each child
		 */
		RefTableDBConnPoolT refTableConnectionPool = db.getXudmlObject()
				.getRefConnectionPools();

		if (refTableConnectionPool != null) {
			refTableConnectionPool.getRefConnectionPool()
					.stream()//
					.forEach(cp -> {
						db.getConnectionPools()
								.add(ConnectionPool.fromRef(cp.getRefId()));

					});
		}

		/**
		 * Note that Schema is unmarshalled here instead of it's own
		 * overloaded method due to weird xudml structure
		 */
		XudmlFolder folder;

		folder = new XudmlFolder(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_SCHEMAURL);
		List<String> idList = folder.getResources()
				.stream()
				.map(resource -> resource.getFilename())
				.map(name -> name.replace(".xml", ""))
				.collect(Collectors.toList());

		for (String id : idList) {

			Schema schema = new Schema(id);
			XudmlMarshaller<SchemaW> schemaMarshaller = new XudmlMarshaller<SchemaW>();
			schema.setXudmlObject(
					schemaMarshaller.unmarshall(ResourceFactory.fromURL("file:" + schema.getResourceUri())));
			String databaseRef = schema.getXudmlObject()
					.getContainerRef();
			if (databaseRef.contains(db.getId())) {
				db.getSchemas()
						.add(schema);
			} else {
				log.debug("Skipped  " + schema.getResourceUri());
			}

		}

		return db;
	}
	
	
	@Override
	public Schema operate(Schema schema) {

		/**
		 * Unmarshalling of schema already done along with the Database - because of weird
		 * XUDML structure. database XUDML has no references to the Children
		 * We unmarshall Physical Tables here, again due to the weird xudml structure
		 */
		
		
		XudmlFolder folder;

		folder = new XudmlFolder(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_PHYSICALTABLEURL);
		List<String> idList = folder.getResources()
				.stream()
				.map(resource -> resource.getFilename())
				.map(name -> name.replace(".xml", ""))
				.collect(Collectors.toList());

		for (String id : idList) {

			PhysicalTable table = new PhysicalTable(id);
			XudmlMarshaller<PhysicalTableW> tableMarshaller = new XudmlMarshaller<PhysicalTableW>();
			table.setXudmlObject(
					tableMarshaller.unmarshall(ResourceFactory.fromURL("file:" + table.getResourceUri())));
			String schemaRef = table.getXudmlObject()
					.getContainerRef();
			if (schemaRef.contains(schema.getId())) {
				schema.getPhysicalTables()
						.add(table);
			} else {
				log.debug("Skipped  " + table.getResourceUri());
			}
		}
		
		return schema;

	}
	
	@Override
	public PhysicalTable operate(PhysicalTable table) {
		/**
		 * Unmarshalling already done along with the Schema - because of weird
		 * XUDML structure. Model XUDML has no references to the Children
		 */
		return table;

	}

}
