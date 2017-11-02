package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.Operable;
import com.globi.rpd.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.PresentationCatalogW;

@Data
@EqualsAndHashCode(callSuper=true)
public class PresentationCatalog extends MarshalledRpdComponent<PresentationCatalogW> implements Operable{



	private final List<PresentationTable> presentationTables = new ArrayList<PresentationTable>();

	public PresentationCatalog(String id) {
		super(id);
	}

	public static PresentationCatalog fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		PresentationCatalog newCatalog=new PresentationCatalog(id);
		newCatalog.setResourceUri(resourceUri);
		return newCatalog;
	}

	@Override
	public <R> R apply(Operator<R> anOperator) {

		return anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Presentation Catalog:" + this.getXudmlObject().getName();
	}

	
}
