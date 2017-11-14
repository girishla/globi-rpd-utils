package com.globi.rpd.component;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import xudml.PresentationLevelW;


@Data
public class PresentationLevel implements Operable<RpdComponent>,RpdComponent {

	private PresentationLevelW xudmlObject;

	public PresentationLevel(PresentationLevelW col) {

		this.xudmlObject = col;

	}

	@Override
	public PresentationLevel apply(Operator<? extends RpdComponent> anOperator){
		return (PresentationLevel)anOperator.operate(this);
	}
	
	@Override
	public PresentationLevel applyWithInput(Operator<? extends RpdComponent> anOperator,TableColumnMetadataDTO dto) {
		return (PresentationLevel)anOperator.operate(this);
	}

	
	@Override
	public String toString(){
		return " Presentation Level:" + xudmlObject.getName();
	}
	
	public String getId(){
		
		return xudmlObject.getId();
		
	}
	
	public void setId(String id){
		xudmlObject.setMdsid("m" + id);
	}

	@Override
	public String getName() {
		return this.xudmlObject.getName();
	}
	

}
