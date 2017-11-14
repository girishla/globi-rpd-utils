
package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.PhysicalTableW;

@Data
@EqualsAndHashCode(callSuper=true)
public class PhysicalTable extends MarshalledRpdComponent<PhysicalTableW> implements Operable<RpdComponent>{


	private final List<PhysicalColumn> physicalColumns = new ArrayList<PhysicalColumn>();
	

	public PhysicalTable(String id) {
		super(id);
	}
	
	public static PhysicalTable fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		PhysicalTable newPhysicalTable=new PhysicalTable(id);
		newPhysicalTable.setResourceUri(resourceUri);
		return newPhysicalTable;
	}

	
	@Override
	public PhysicalTable apply(Operator<? extends RpdComponent> anOperator) {
		return (PhysicalTable)anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  PhysicalTable:" + this.getXudmlObject().getName();
	}


}

