
package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.BusinessModelW;

@Data
@EqualsAndHashCode(callSuper=true)
public class BusinessModel extends MarshalledRpdComponent<BusinessModelW> implements Operable<RpdComponent>{


	private final List<LogicalTable> logicalTables = new ArrayList<LogicalTable>();

	public BusinessModel(String id) {
		super(id);
	}
	
	public static BusinessModel fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		BusinessModel newModel=new BusinessModel(id);
		newModel.setResourceUri(resourceUri);
		return newModel;
	}

	
	@Override
	public BusinessModel apply(Operator<? extends RpdComponent> anOperator) {
		return (BusinessModel)anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Business Model:" + this.getXudmlObject().getName();
	}


}

