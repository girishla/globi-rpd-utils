
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.operator.Operator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CatalogDefaultTraverser implements Traverser {

	@Override
	public void traverse(PresentationCatalog presCatalog, Operator anOperator) {

		for (PresentationTable presTable : presCatalog.getPresentationTables()) {
			
			presTable.apply(anOperator);
		}
	}

	@Override
	public void traverse(PresentationTable presTable, Operator anOperator) {

		for (PresentationColumn presColumn : presTable.getPresentationColumns()) {
			presColumn.apply(anOperator);
		}

	}

}
