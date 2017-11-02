package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.Operable;
import com.globi.rpd.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.BusinessModelW;


@Data
@EqualsAndHashCode(callSuper=true)
public class LogicalTable extends MarshalledRpdComponent<BusinessModelW> implements Operable {

	private final List<LogicalColumn> logicalColumns = new ArrayList<LogicalColumn>();

	public LogicalTable(String id) {
		super(id);
	}
	
	public static LogicalTable fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		LogicalTable newTable=new LogicalTable(id);
		newTable.setResourceUri(resourceUri);
		return newTable;
	}
	
	@Override
	public <R> R apply(Operator<R> anOperator) {
		return anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Logical Table:" + this.getXudmlObject().getName();
	}


}
