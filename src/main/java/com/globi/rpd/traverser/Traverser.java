
package com.globi.rpd.traverser;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.ConnectionPool;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalForeignKey;
import com.globi.rpd.component.PhysicalKey;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
import com.globi.rpd.component.StandardRpd;
import com.globi.rpd.operator.InputOperator;
import com.globi.rpd.operator.Operator;

public interface Traverser {

	default public void traverse(StandardRpd rpd, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"StandardRpd Traverser" + "**************Warning: Default Method invocation*******************");
		
	}
	
	
	default public void traverse(PresentationCatalog presCatalog, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"PresentationCatalog Traverser" + "**************Warning: Default Method invocation*******************");
		
	}

	default public void traverse(PresentationTable prestable, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		
	}

	default public void traverse(PresentationColumn presColumn, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		

	}

	default public void traverse(BusinessModel model, Operator<? extends RpdComponent> anOperator) {
		// default no-op
	}

	default public void traverse(LogicalTable logicalTable, Operator<? extends RpdComponent> anOperator) {
		// default no-op
	}

	default public void traverse(LogicalColumn logicalColumn, Operator<? extends RpdComponent> anOperator) {
		// default no-op
	}

	default public void traverse(PresentationHierarchy presHierarchy, Operator<? extends RpdComponent> anOperator) {
		// default no-op
	}

	default public void traverse(Database database, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
	}

	
	default public void traverse(Schema database, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"Database Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(ConnectionPool database, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"ConnectionPool Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(PhysicalTable database, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalTable Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(PhysicalColumn database, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalColumn Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(PhysicalKey database, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalKey Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(PhysicalForeignKey database, Operator<? extends RpdComponent> anOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalForeignKey Traverser" + "**************Warning: Default Method invocation*******************");
	}
	
	
	
	
	
	
	
	
	
	/**************************************************************************************
	 * 
	 * WITH INPUT
	 * 
	 **************************************************************************************/
	
	
	default public void traverse(StandardRpd rpd, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"StandardRpd Traverser" + "**************Warning: Default Method invocation*******************");
		
	}
	
	
	default public void traverse(PresentationCatalog presCatalog, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"PresentationCatalog Traverser" + "**************Warning: Default Method invocation*******************");
		
	}

	default public void traverse(PresentationTable prestable, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		
	}

	default public void traverse(PresentationColumn presColumn, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		

	}

	default public void traverse(BusinessModel model, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
	}

	default public void traverse(LogicalTable logicalTable, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
	}

	default public void traverse(LogicalColumn logicalColumn, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
	}

	default public void traverse(PresentationHierarchy presHierarchy, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
	}

	default public void traverse(Database database, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
	}

	
	default public void traverse(Schema database, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"Database Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(ConnectionPool database, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"ConnectionPool Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(PhysicalTable database, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalTable Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(PhysicalColumn database, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalColumn Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(PhysicalKey database, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalKey Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(PhysicalForeignKey database, InputOperator<? extends RpdComponent> anInputOperator) {
		// default no-op
		
		System.out.println(
				"PhysicalForeignKey Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}
