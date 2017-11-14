
package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.SchemaW;

@Data
@EqualsAndHashCode(callSuper=true)
public class Schema extends MarshalledRpdComponent<SchemaW> implements Operable<RpdComponent>{


	private final List<PhysicalTable> physicalTables = new ArrayList<PhysicalTable>();
	

	public Schema(String id) {
		super(id);
	}
	
	public static Schema fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		Schema newSchema=new Schema(id);
		newSchema.setResourceUri(resourceUri);
		return newSchema;
	}

	
	@Override
	public Schema apply(Operator<? extends RpdComponent> anOperator) {
		return (Schema)anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Schema:" + this.getXudmlObject().getName();
	}


}

