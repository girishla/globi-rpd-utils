package com.globi.rpd.operator;

import java.io.File;
import java.util.List;

import com.globi.rpd.AppProperties;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationLevel;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

import xudml.LogicalColumnW;
import xudml.PresentationColumnW;
import xudml.PresentationLevelW;



/**
 * Allows each RPD component to hydrate itself with any child components etc. 
 * @author Girish Lakshmanan
 *
 */
public class HydratingOperator implements Operator<RpdComponent> {

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		if (presCatalog.getXudmlObject() == null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");

		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		if (presTable.getXudmlObject() == null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");

		for (PresentationColumnW column : presTable.getXudmlObject()
				.getPresentationColumn()) {
			presTable.getPresentationColumns()
					.add(new PresentationColumn(column));
		}

		return presTable;

	}

	@Override
	public PresentationHierarchy operate(PresentationHierarchy presHierarchy) {

		if (presHierarchy.getXudmlObject() == null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");

		for (PresentationLevelW column : presHierarchy.getXudmlObject()
				.getPresentationLevel()) {
			presHierarchy.getPresentationLevels()
					.add(new PresentationLevel(column));
		}

		return presHierarchy;

	}

	@Override
	public BusinessModel operate(BusinessModel model) {

		if (model.getXudmlObject() == null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");

		XudmlFolder folder = new XudmlFolder(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_LOGICALJOIN);

		/**
		 * Hydrate Logical joins
		 */
		List<File> fileList = folder.getFiles();
		for (File file : fileList) {
			String resourceUri = file.getAbsolutePath();
			LogicalComplexJoin join = LogicalComplexJoin.fromResource(resourceUri);
			XudmlUnmarshallingOperator unmarshaller = new XudmlUnmarshallingOperator();
			unmarshaller.operate(join);

			String table1Id = join.getXudmlObject()
					.getLogicalTable1Ref()
					.split(".xml#m")[1];

			boolean tableFoundInModel = model.getLogicalTables()
					.stream()
					.anyMatch(t -> t.getId()
							.equals(table1Id));

			if (tableFoundInModel) {
				model.getLogicalComplexJoins()
						.add(join);
			}

		}

		return model;

	}

	@Override
	public LogicalTable operate(LogicalTable table) {

		if (table.getXudmlObject() == null)
			throw new IllegalStateException("Cannot hydrate withour a XUDML instance set");

		for (LogicalColumnW column : table.getXudmlObject()
				.getLogicalColumn()) {
			table.getLogicalColumns()
					.add(new LogicalColumn(column));
		}

		return table;

	}

}
