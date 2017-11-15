
package com.globi.rpd.operator;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.ConnectionPool;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.LogicalColumn;
import com.globi.rpd.component.LogicalComplexJoin;
import com.globi.rpd.component.LogicalTable;
import com.globi.rpd.component.PhysicalColumn;
import com.globi.rpd.component.PhysicalForeignKey;
import com.globi.rpd.component.PhysicalKey;
import com.globi.rpd.component.PhysicalTable;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationColumn;
import com.globi.rpd.component.PresentationHierarchy;
import com.globi.rpd.component.PresentationLevel;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;
import com.globi.rpd.component.Schema;
import com.globi.rpd.component.StandardRpd;

public interface InputOperator<R extends RpdComponent> {

	default public R operate(PresentationCatalog prescatalog, List<TableColumnMetadataDTO> dto) {
//
//		System.out.println(
//				"PresentationCatalog Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(PresentationTable presTable, List<TableColumnMetadataDTO> dto) {
//		System.out.println(
//				"PresentationTable Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(PresentationColumn presColumn, List<TableColumnMetadataDTO> dto) {
//		System.out.println(
//				"PresentationColumn Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(BusinessModel model, List<TableColumnMetadataDTO> dto) {
//		System.out.println(
//				"BusinessModel Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(LogicalTable table, List<TableColumnMetadataDTO> dto) {
//		System.out.println(
//				"LogicalTable Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(LogicalColumn column, List<TableColumnMetadataDTO> dto) {
//		System.out.println("LogicalColumn " + column.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	
	
	default public R operate(LogicalComplexJoin column, List<TableColumnMetadataDTO> dto) {
//		System.out.println("LogicalColumn " + column.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	
	default public R operate(StandardRpd rpd, List<TableColumnMetadataDTO> dto) {
//		System.out.println("StandardRpd " + rpd.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	default public R operate(PresentationHierarchy presHierarchy, List<TableColumnMetadataDTO> dto) {
//		System.out.println("StandardRpd " + rpd.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	
	
	default public R operate(PresentationLevel presLevel, List<TableColumnMetadataDTO> dto) {
//		System.out.println("StandardRpd " + rpd.getName()
//				+ "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	
	default public R operate(Database database, List<TableColumnMetadataDTO> dto) {
		System.out.println(
				"Database Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}

	
	default public R operate(ConnectionPool schema, List<TableColumnMetadataDTO> dto) {
		System.out.println(
				"ConnectionPool Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}
	
	default public R operate(Schema schema, List<TableColumnMetadataDTO> dto) {
		System.out.println(
				"Schema Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}
	
	
	default public R operate(PhysicalTable physicalTable, List<TableColumnMetadataDTO> dto) {
		System.out.println(
				"PhysicalTable TableColumnDTO Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}
	
	default public R operate(PhysicalColumn physicalColumn, List<TableColumnMetadataDTO> dto) {
		System.out.println(
				"PhysicalColumn Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}
	
	default public R operate(PhysicalKey physicalColumn, List<TableColumnMetadataDTO> dto) {
		System.out.println(
				"PhysicalKey Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}
	
	default public R operate(PhysicalForeignKey physicalColumn, List<TableColumnMetadataDTO> dto) {
		System.out.println(
				"PhysicalForeignKey Operator" + "**************Warning: Default Method invocation*******************");
		R returnVal = null;
		return returnVal;
	}
	
	
}
