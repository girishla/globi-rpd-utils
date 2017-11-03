package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.LogicalTableW;


@Data
@EqualsAndHashCode(callSuper=true)
public class LogicalTable extends MarshalledRpdComponent<LogicalTableW> implements Operable<RpdComponent> {

	private final List<LogicalColumn> logicalColumns = new ArrayList<LogicalColumn>();

	public LogicalTable(String id) {
		super(id);
		
		this.setResourceUri(XudmlConstants.XUDML_BASEURL + "/oracle/bi/server/base/LogicalTable/" + id + ".xml");

	}
	
	public static LogicalTable fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		LogicalTable newTable=new LogicalTable(id);
		newTable.setResourceUri(resourceUri);
		return newTable;
	}
	
	@Override
	public LogicalTable apply(Operator<RpdComponent> anOperator) {
		return (LogicalTable)anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Logical Table:" + this.getXudmlObject().getName();
	}


}
