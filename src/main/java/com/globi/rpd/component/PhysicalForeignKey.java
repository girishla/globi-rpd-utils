
package com.globi.rpd.component;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.InputOperator;
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
	public PhysicalForeignKey applyWithInput(InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> dto) {
		return (PhysicalForeignKey)anOperator.operate(this,dto);
	}


	public String getId() {
		return xudmlObject.getId();
	}

	public String getName() {
		return xudmlObject.getName();
	}
	
}
