package com.globi.rpd.component;

import com.globi.rpd.Operable;
import com.globi.rpd.Operator;

import lombok.Data;
import xudml.PresentationColumnW;

@Data
public class PresentationColumn implements Operable {

	private PresentationColumnW xudmlObject;

	public PresentationColumn(PresentationColumnW col) {

		this.xudmlObject = col;

	}
	

	@Override
	public <R, E extends Throwable> R apply(Operator<R, E> anOperator) throws E {
		return anOperator.operate(this);
	}
	
	@Override
	public String toString(){
		
		return "  Column:" + xudmlObject.getName();
		
	}
	

}
