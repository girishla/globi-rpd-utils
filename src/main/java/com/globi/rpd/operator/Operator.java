
package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;


public interface Operator {


	default public PresentationCatalog operate(PresentationCatalog prescatalog) {
			System.out.println("PresentationCatalog " +  "**************Warning: Default Method invocation*******************");
		
		return null;
	}

	default public PresentationTable operate(PresentationTable presTable) {
		System.out.println("PresentationTable " + "**************Warning: Default Method invocation*******************");
		return null;
	}

	default public PresentationColumn operate(PresentationColumn presColumn) {
		System.out.println("PresentationColumn" + "**************Warning: Default Method invocation*******************");
		return null;
	}

	default public BusinessModel operate(BusinessModel model) {
		System.out.println("BusinessModel " + "**************Warning: Default Method invocation*******************");
		return null;
	}

	default public LogicalTable operate(LogicalTable table) {
		System.out.println("LogicalTable " + "**************Warning: Default Method invocation*******************");
		return null;
	}

	default public LogicalColumn operate(LogicalColumn column) {
		System.out.println("LogicalColumn " + column.getName() + "**************Warning: Default Method invocation*******************");
		return null;
	}

}
