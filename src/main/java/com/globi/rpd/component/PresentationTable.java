package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.AppProperties;
import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.InputOperator;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.PresentationTableW;

@Data
@EqualsAndHashCode(callSuper=true)
public class PresentationTable extends MarshalledRpdComponent<PresentationTableW> implements Operable<RpdComponent> {


	/**
	 * Represents a FULL reference to the {@link PresentationTable} in the
	 * format {@code parentCatalogid - tableId }. This is then used to parse and
	 * derive all the other ref related fields
	 * example: m40000456-6dc5-167d-806e-c0a838100000-m40000457-6dc5-167d-806e-c0a838100000
	 */
	private String refId;

	/**
	 * path reference to the table
	 * example: oracle/bi/server/base/PresentationTable/40000568-6dc5-167d-806e-c0a838100000.xml#m40000568-6dc5-167d-806e-c0a838100000
	 */
	private String ref;

	// m40000456-6dc5-167d-806e-c0a838100000
	private String parentRefId;

	/// oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml#m40000456-6dc5-167d-806e-c0a838100000
	private String parentRef;

	private final List<PresentationColumn> presentationColumns = new ArrayList<PresentationColumn>();
	private final List<PresentationHierarchy> presentationHierarchies = new ArrayList<PresentationHierarchy>();

	public PresentationTable(String refId) {

		super(refId.split("-m")[1]);
		
		this.refId = refId;
		this.setId(refId.split("-m")[1]);
		this.setResourceUri(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_PRESTABLEURL + this.getId() + ".xml");
		this.ref =  XudmlConstants.XUDML_PRESTABLEURL  + this.getId()  + ".xml#m" + this.getId() ;
		this.parentRefId = refId.split("-m")[0];
		this.parentRef = XudmlConstants.XUDML_CATALOGURL + parentRefId.substring(1) + ".xml#"
				+ parentRefId;
	}
	
	@Override
	public PresentationTable apply(Operator<? extends RpdComponent> anOperator) {
		return (PresentationTable)anOperator.operate(this);
	}
	
	@Override
	public PresentationTable applyWithInput(InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> dto) {
		return (PresentationTable)anOperator.operate(this,dto);
	}


	@Override
	public String toString() {
		return "  Presentation Table:" + this.getXudmlObject().getName();
	}


}
