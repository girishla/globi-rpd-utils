
package com.globi.rpd.component;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import xudml.PhysicalKeyW;

@Data
public class PhysicalKey implements Operable<RpdComponent>, RpdComponent {
	private PhysicalKeyW xudmlObject;

	public PhysicalKey(PhysicalKeyW col) {
		this.xudmlObject = col;
	}

	@Override
	public String toString() {
		return "  Physical Key:" + this.getXudmlObject()
				.getName();
	}

	@Override
	public PhysicalKey apply(Operator<? extends RpdComponent> anOperator) {
		return (PhysicalKey) anOperator.operate(this);
	}

	public String getId() {
		return xudmlObject.getId();
	}

	public String getName() {
		return xudmlObject.getName();
	}
	
}