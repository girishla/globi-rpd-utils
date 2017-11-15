package com.globi.rpd;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableColumnMetadataDTO {
	
	private String tableName;
	private String tableType;
	private int columnOrder;
	private String colName;
	private String colLogicalName;
	private String colType;
	private String colDataType;
	private int colPrecision;
	private int colScale;
	private String colNullFlag;
	private String colSubType;
	private String dimTablename;
	private String dimTableAlias;
	private String LogicalTableName;
	private String LtsName;
	//If not provided, 'Detail' will be used
	private String ContentLevelName;
	//This will be blank for the root of the Hierarchy
	private String parentContentLevelName;

	
}
