package com.globi.rpd.component;

import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.InputOperator;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import xudml.PresentationColumnW;

@Data
public class PresentationColumn implements Operable<RpdComponent>,RpdComponent {

	private PresentationColumnW xudmlObject;

	public PresentationColumn(PresentationColumnW col) {

		this.xudmlObject = col;

	}

	@Override
	public PresentationColumn apply(Operator<? extends RpdComponent> anOperator){
		return (PresentationColumn)anOperator.operate(this);
	}
	
	@Override
	public PresentationColumn applyWithInput(InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> dto) {
		return (PresentationColumn)anOperator.operate(this,dto);
	}

	
	@Override
	public String toString(){
		return " Presentation Column:" + xudmlObject.getName();
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
