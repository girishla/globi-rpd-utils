package com.globi.rpd.component;

import com.globi.rpd.AppProperties;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.LogicalComplexJoinW;


@Data
@EqualsAndHashCode(callSuper=true)
public class LogicalComplexJoin extends MarshalledRpdComponent<LogicalComplexJoinW> implements Operable<RpdComponent> {


	public LogicalComplexJoin(String id) {
		super(id);
		
		this.setResourceUri(AppProperties.INSTANCE.getBasePath() + "/oracle/bi/server/base/LogicalComplexJoin/" + id + ".xml");

	}
	
	public static LogicalComplexJoin fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		LogicalComplexJoin newJoin=new LogicalComplexJoin(id);
		newJoin.setResourceUri(resourceUri);
		return newJoin;
	}
	
	@Override
	public LogicalComplexJoin apply(Operator<? extends RpdComponent> anOperator) {
		return (LogicalComplexJoin)anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Logical complex Join:" + this.getXudmlObject().getName();
	}


}
