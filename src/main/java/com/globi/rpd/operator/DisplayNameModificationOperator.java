package com.globi.rpd.operator;

import java.util.function.UnaryOperator;

import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.component.PresentationTable;
import com.globi.rpd.component.RpdComponent;

import xudml.AliasW;

public class DisplayNameModificationOperator implements Operator<RpdComponent> {

	private final UnaryOperator<String> stringTransformer;

	public DisplayNameModificationOperator(UnaryOperator<String> stringTransformer) {

		this.stringTransformer = stringTransformer;

	}

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) {

		if (presCatalog.getXudmlObject() == null)
			throw new IllegalStateException("Cannot process without a XUDML instance set");


		AliasW alias = new AliasW();
		alias.setName(presCatalog.getXudmlObject().getName());
		presCatalog.getXudmlObject().setDispName(stringTransformer.apply(presCatalog.getXudmlObject().getName()));
		presCatalog.getXudmlObject().setHasDispName(true);

		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) {

		if (presTable.getXudmlObject() == null)
			throw new IllegalStateException("Cannot process without a XUDML instance set");

		// nothing to Modify yet

		return presTable;

	}

}
