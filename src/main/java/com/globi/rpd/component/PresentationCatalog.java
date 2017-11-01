package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.Operable;
import com.globi.rpd.Operator;
import com.globi.rpd.RpdMarshalledObject;
import com.globi.rpd.RpdObject;

import lombok.Data;
import xudml.PresentationCatalogW;

@Data
public class PresentationCatalog implements Operable, RpdObject, RpdMarshalledObject<PresentationCatalogW> {

	// m40000457-6dc5-167d-806e-c0a838100000
	private String id;
	private String resourceUri;
	private PresentationCatalogW xudmlObject;

	private final List<PresentationTable> presentationTables = new ArrayList<PresentationTable>();

	public PresentationCatalog(String resourceUri) {
		this.resourceUri = resourceUri;
	}

	@Override
	public <R, E extends Throwable> R apply(Operator<R, E> anOperator) throws E {
		return anOperator.operate(this);
	}

	@Override
	public String toString() {

		return "  Subject Area:" + xudmlObject.getName();

	}

	@Override
	public PresentationCatalogW getXudmlObject() {
		return this.xudmlObject;
	}

	@Override
	public String getName() {
		return this.xudmlObject.getName();
	}

}