package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.Operable;
import com.globi.rpd.Operator;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.PresentationTableW;

@Data
@EqualsAndHashCode(callSuper=true)
public class PresentationTable extends MarshalledRpdComponent<PresentationTableW> implements Operable {


	/**
	 * Represents a FULL reference to the {@link PresentationTable} in the
	 * format {@code parentCatalogid - tableId }. This is then used to parse and
	 * derive all the other ref related fields
	 */
	// m40000456-6dc5-167d-806e-c0a838100000-m40000457-6dc5-167d-806e-c0a838100000
	private String refId;
	/// oracle/bi/server/base/PresentationTable/40000568-6dc5-167d-806e-c0a838100000.xml#m40000568-6dc5-167d-806e-c0a838100000
	private String ref;

	// m40000456-6dc5-167d-806e-c0a838100000
	private String parentRefId;

	/// oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml#m40000456-6dc5-167d-806e-c0a838100000
	private String parentRef;

	private final List<PresentationColumn> presentationColumns = new ArrayList<PresentationColumn>();

	public PresentationTable(String refId) {

		super(refId.split("-m")[1]);
		
		this.refId = refId;
		this.setId(refId.split("-m")[1]);
		this.setResourceUri(XudmlConstants.XUDML_BASEURL + "/oracle/bi/server/base/PresentationTable/" + this.getId() + ".xml");
		this.ref = "/oracle/bi/server/base/PresentationTable/" + this.getId()  + ".xml#m" + this.getId() ;
		this.parentRefId = refId.split("-m")[0];
		this.parentRef = "/oracle/bi/server/base/PresentationCatalog/" + parentRefId.substring(1) + ".xml#"
				+ parentRefId;
	}

	
	@Override
	public <R> R apply(Operator<R> anOperator) {
		return anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  Presentation Table:" + this.getXudmlObject().getName();
	}


}
