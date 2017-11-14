package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.AppProperties;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.LogicalTableW;


@Data
@EqualsAndHashCode(callSuper=true,exclude={"joinedToDimensions", "joinedFromFacts"})
public class LogicalTable extends MarshalledRpdComponent<LogicalTableW> implements Operable<RpdComponent> {

	private final List<LogicalColumn> logicalColumns = new ArrayList<LogicalColumn>();
	private final List<LogicalTable> joinedToDimensions=new ArrayList<>();
	private final List<LogicalTable> joinedFromFacts=new ArrayList<>();

	public LogicalTable(String id) {
		super(id);
		
		this.setResourceUri(AppProperties.INSTANCE.getBasePath()+ "/oracle/bi/server/base/LogicalTable/" + id + ".xml");

	}
	
	public static LogicalTable fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		LogicalTable newTable=new LogicalTable(id);
		newTable.setResourceUri(resourceUri);
		return newTable;
	}
	
	@Override
	public LogicalTable apply(Operator<? extends RpdComponent> anOperator) {
		return (LogicalTable)anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Logical Table:" + this.getXudmlObject().getName();
	}
	
	
	public boolean isFactTable(){
		return !this.joinedToDimensions.isEmpty();
	
	}
	
	public boolean isDimTable(){
		return !this.joinedFromFacts.isEmpty();
	
	}


}
