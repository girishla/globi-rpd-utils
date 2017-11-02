
package com.globi.rpd;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;

public interface Operator {


	default public PresentationCatalog operate(PresentationCatalog prescatalog) {
		return null;
	}

	default public PresentationTable operate(PresentationTable presTable) {
		return null;
	}

	default public PresentationColumn operate(PresentationColumn presColumn) {
		return null;
	}

	default public BusinessModel operate(BusinessModel model) {
		return null;
	}

	default public LogicalTable operate(LogicalTable model) {
		return null;
	}

	default public LogicalColumn operate(LogicalColumn model) {
		return null;
	}

}
