
package com.globi.rpd.component;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import xudml.PhysicalColumnW;

@Data
public class PhysicalColumn implements Operable<RpdComponent>, RpdComponent {
	private PhysicalColumnW xudmlObject;

	public PhysicalColumn(PhysicalColumnW col) {
		this.xudmlObject = col;
	}

	@Override
	public String toString() {
		return "  Physical Column:" + this.getXudmlObject()
				.getName();
	}

	@Override
	public PhysicalColumn apply(Operator<? extends RpdComponent> anOperator) {
		return (PhysicalColumn) anOperator.operate(this);
	}
	
	@Override
	public PhysicalColumn applyWithInput(Operator<? extends RpdComponent> anOperator,TableColumnMetadataDTO dto) {
		return (PhysicalColumn)anOperator.operate(this);
	}


	public String getId() {
		return xudmlObject.getId();
	}

	public String getName() {
		return xudmlObject.getName();
	}
	
}
