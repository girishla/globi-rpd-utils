package com.globi.rpd.presentationcatalog;

import com.globi.Visitable;
import com.globi.Operator;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xudml.PresentationColumnW;

@Data
@Slf4j
public class PresentationColumn implements Visitable {

	private PresentationColumnW xudmlObject;

	public PresentationColumn(PresentationColumnW col) {

		this.xudmlObject = col;

	}

	@Override
	public <R, E extends Throwable> R accept(Operator<R, E> anOperator) throws E {
		return anOperator.operate(this);
	}

}
