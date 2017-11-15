package com.globi.rpd;

import lombok.Data;

@Data
public class TableColumnMetadataDTO {
	
	private String tableName;
	private String tableType;
	private int columnOrder;
	private String colName;
	private String colType;
	private String colDataType;
	private String colPrecision;
	private String colScale;
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
