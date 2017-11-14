
package com.globi.rpd.component;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import xudml.PhysicalForeignKeyW;

@Data
public class PhysicalForeignKey implements Operable<RpdComponent>, RpdComponent {
	private PhysicalForeignKeyW xudmlObject;

	public PhysicalForeignKey(PhysicalForeignKeyW col) {
		this.xudmlObject = col;
	}

	@Override
	public String toString() {
		return "  Physical Foreign Key:" + this.getXudmlObject()
				.getName();
	}

	@Override
	public PhysicalForeignKey apply(Operator<? extends RpdComponent> anOperator) {
		return (PhysicalForeignKey) anOperator.operate(this);
	}
	
	
	@Override
	public PhysicalForeignKey applyWithInput(Operator<? extends RpdComponent> anOperator,TableColumnMetadataDTO dto) {
		return (PhysicalForeignKey)anOperator.operate(this);
	}


	public String getId() {
		return xudmlObject.getId();
	}

	public String getName() {
		return xudmlObject.getName();
	}
	
}
