package com.globi.rpd.traverser;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
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

public interface InputTraverser {
	
	
	
	default public void traverse(StandardRpd rpd, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"StandardRpd Traverser" + "**************Warning: Default Method invocation*******************");
		
	}
	
	
	default public void traverse(PresentationCatalog presCatalog, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"PresentationCatalog Traverser" + "**************Warning: Default Method invocation*******************");
		
	}

	default public void traverse(PresentationTable prestable, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		
	}

	default public void traverse(PresentationColumn presColumn, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		

	}

	default public void traverse(BusinessModel model, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
	}

	default public void traverse(LogicalTable logicalTable, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
	}

	default public void traverse(LogicalColumn logicalColumn, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
	}

	default public void traverse(PresentationHierarchy presHierarchy, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
	}

	default public void traverse(Database database, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
	}

	
	default public void traverse(Schema database, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"Database Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(ConnectionPool database, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"ConnectionPool Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(PhysicalTable database, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"PhysicalTable Traverser" + "**************Warning: Default Method invocation*******************");
	}

	default public void traverse(PhysicalColumn database, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"PhysicalColumn Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(PhysicalKey database, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"PhysicalKey Traverser" + "**************Warning: Default Method invocation*******************");
	}

	
	default public void traverse(PhysicalForeignKey database, InputOperator<? extends RpdComponent> anInputOperator,List<TableColumnMetadataDTO> dto) {
		// default no-op
		
		System.out.println(
				"PhysicalForeignKey Traverser" + "**************Warning: Default Method invocation*******************");
	}

	

	

}
