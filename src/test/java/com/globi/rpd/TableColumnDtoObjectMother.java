package com.globi.rpd;

public class TableColumnDtoObjectMother {

	public static TableColumnMetadataDTO getExampleDimLogicalColumn() {

		return TableColumnMetadataDTO.builder()
				.dimTableAlias("Dim_Campaign")
				.dimTablename("D_CAMPAIGN")
				.LogicalTableName("Campaign")
				.LtsName("Dim_Campaign")
				.ContentLevelName("Detail")
				.parentContentLevelName("Total")
				.colDataType("VARCHAR2")
				.colName("ACTIVE_FLAG")
				.colLogicalName("Active Flag")
				.colNullFlag("N")
				.colPrecision(1)
				.colScale(0)
				.colSubType("")
				.colType("Attribute")
				.columnOrder(1)
				.build();
	}

}
