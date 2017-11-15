package com.globi.rpd.component;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.InputOperator;
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
	
	@Override
	public LogicalColumn applyWithInput(InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> dto) {
		return (LogicalColumn)anOperator.operate(this,dto);
	}

	public String getId() {

		return xudmlObject.getId();

	}

	public String getName() {
		return xudmlObject.getName();

	}
}
