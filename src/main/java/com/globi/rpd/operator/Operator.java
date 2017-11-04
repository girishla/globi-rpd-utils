
package com.globi.rpd.operator;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.dsl.StandardRpd;

public interface Operator<R extends RpdComponent> {

	default public R operate(PresentationCatalog prescatalog) {

		System.out.println(
				"PresentationCatalog Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(PresentationTable presTable) {
		System.out.println(
				"PresentationTable Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(PresentationColumn presColumn) {
//		System.out.println(
//				"PresentationColumn Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(BusinessModel model) {
		System.out.println(
				"BusinessModel Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(LogicalTable table) {
		System.out.println(
				"LogicalTable Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(LogicalColumn column) {
//		System.out.println("LogicalColumn " + column.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	
	
	default public R operate(LogicalComplexJoin column) {
//		System.out.println("LogicalColumn " + column.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	
	default public R operate(StandardRpd rpd) {
//		System.out.println("StandardRpd " + rpd.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}


}
