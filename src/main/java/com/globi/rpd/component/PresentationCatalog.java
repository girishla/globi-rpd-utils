package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.PresentationCatalogW;

@Data
@EqualsAndHashCode(callSuper=true)
public class PresentationCatalog extends MarshalledRpdComponent<PresentationCatalogW> implements Operable<RpdComponent>{



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
	public RpdComponent apply(Operator<RpdComponent> operator) {

		return  operator.operate(this);
	}

	@Override
	public String toString() {
		return "  Presentation Catalog:" + this.getXudmlObject().getName();
	}

	
}
