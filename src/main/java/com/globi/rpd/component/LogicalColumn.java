package com.globi.rpd.component;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import xudml.LogicalColumnW;

@Data
public class LogicalColumn implements Operable<RpdComponent>,RpdComponent {
	private LogicalColumnW xudmlObject;

	public LogicalColumn(LogicalColumnW col) {
		this.xudmlObject = col;
	}

	@Override
	public String toString() {
		return "  Logical Column:" + this.getXudmlObject()
				.getName();
	}

	@Override
	public LogicalColumn apply(Operator<? extends RpdComponent> anOperator) {
		return (LogicalColumn)anOperator.operate(this);
	}

	public String getId() {

		return xudmlObject.getId();

	}

	public String getName() {
		return xudmlObject.getName();

	}
}
