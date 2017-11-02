
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.operator.Operator;

public interface Traverser {

	default public void traverse(PresentationCatalog presCatalog, Operator anOperator) {
		// default no-op
	}

	default public void traverse(PresentationTable prestable, Operator anOperator) {
		// default no-op
	}

	default public void traverse(PresentationColumn presColumn, Operator anOperator) {
		// default no-op
	}

	default public void traverse(BusinessModel model, Operator anOperator) {
		// default no-op
	}

	default public void traverse(LogicalTable logicalTable, Operator anOperator) {
		// default no-op
	}

	default public void traverse(LogicalColumn logicalColumn, Operator anOperator) {
		// default no-op
	}

}
