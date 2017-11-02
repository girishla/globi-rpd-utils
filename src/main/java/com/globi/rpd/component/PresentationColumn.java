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
	public <R> R apply(Operator<R> anOperator){
		return anOperator.operate(this);
	}
	
	@Override
	public String toString(){
		
		return " Presentation Column:" + xudmlObject.getName();
		
	}
	

}
