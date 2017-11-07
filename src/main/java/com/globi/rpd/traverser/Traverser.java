
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.operator.Operator;

public interface Traverser {

	default public void traverse(PresentationCatalog presCatalog, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"PresentationCatalog Traverser" + "**************Warning: Default Method invocation*******************");
		
	}

	default public void traverse(PresentationTable prestable, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"PresentationTable Traverser" + "**************Warning: Default Method invocation*******************");
		
	}

	default public void traverse(PresentationColumn presColumn, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
//		System.out.println(
//				"PresentationColumn Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(BusinessModel model, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"BusinessModel Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(LogicalTable logicalTable, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"LogicalTable Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(LogicalColumn logicalColumn, Operator<? extends RpdComponent> anOperator) {
		// default no-op
//		
//		System.out.println(
//				"LogicalColumn Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(PresentationHierarchy presHierarchy, Operator<? extends RpdComponent> anOperator) {
		// default no-op
//		
//		System.out.println(
//				"LogicalColumn Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	
}
