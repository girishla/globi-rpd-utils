package com.globi.rpd;

import java.util.function.UnaryOperator;

import com.globi.rpd.presentationcatalog.PresentationCatalog;
import com.globi.rpd.presentationcatalog.PresentationTable;

import lombok.Setter;
import xudml.AliasW;

public class DisplayNameModificationOperator extends BaseOperator<Object, Exception> {

	private final UnaryOperator<String> stringTransformer;

	public DisplayNameModificationOperator(UnaryOperator<String> stringTransformer) {

		this.stringTransformer = stringTransformer;

	}

	@Override
	public PresentationCatalog operate(PresentationCatalog presCatalog) throws Exception {

		if (presCatalog.getXudmlObject() == null)
			throw new IllegalStateException("Cannot process without a XUDML instance set");


		AliasW alias = new AliasW();
		alias.setName(presCatalog.getXudmlObject().getName());
		presCatalog.getXudmlObject().getAlias().add(alias);

		String newDispName = stringTransformer.apply(presCatalog.getXudmlObject().getName());
		presCatalog.getXudmlObject().setDispName(newDispName);
		presCatalog.getXudmlObject().setHasDispName(true);

		return presCatalog;
	}

	@Override
	public PresentationTable operate(PresentationTable presTable) throws Exception {

		if (presTable.getXudmlObject() == null)
			throw new IllegalStateException("Cannot process withour a XUDML instance set");

		// nothing to Modify yet

		return presTable;

	}

}
