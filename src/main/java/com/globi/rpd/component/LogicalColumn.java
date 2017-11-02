package com.globi.rpd.component;

import lombok.Data;
import xudml.LogicalColumnW;

@Data
public class LogicalColumn {
	private LogicalColumnW xudmlObject;
	
	
	public LogicalColumn(LogicalColumnW col) {

		this.xudmlObject = col;

	}
	

	@Override
	public String toString() {
		return "  Logical Column:" + this.getXudmlObject().getName();
	}

	
}
