
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
import xudml.PresentationHierarchyW;

@Data
@EqualsAndHashCode(callSuper=true)
public class PresentationHierarchy extends MarshalledRpdComponent<PresentationHierarchyW> implements Operable<RpdComponent> {


	/**
	 * Represents a FULL reference to the {@link PresentationHierarchy} in the
	 * format {@code parentTableid - hierarchyId }. This is then used to parse and
	 * derive all the other ref related fields
	 * example: m40000456-6dc5-167d-806e-c0a838100000-m40000457-6dc5-167d-806e-c0a838100000
	 */
	private String refId;

	/**
	 * path reference to the table
	 * example: oracle/bi/server/base/PresentationHierarchy/40000568-6dc5-167d-806e-c0a838100000.xml#m40000568-6dc5-167d-806e-c0a838100000
	 */
	private String ref;

	// m40000456-6dc5-167d-806e-c0a838100000
	private String parentRefId;

	/// oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml#m40000456-6dc5-167d-806e-c0a838100000
	private String parentRef;

	private final List<PresentationLevel> presentationLevels = new ArrayList<PresentationLevel>();

	public PresentationHierarchy(String refId) {

		super(refId.split("-m")[1]);
		
		this.refId = refId;
		this.setId(refId.split("-m")[1]);
		this.setResourceUri(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_PRESHIERARCHY + this.getId() + ".xml");
		this.ref = XudmlConstants.XUDML_PRESHIERARCHY + this.getId()  + ".xml#m" + this.getId() ;
		this.parentRefId = refId.split("-m")[0];
		this.parentRef = XudmlConstants.XUDML_PRESTABLEURL + parentRefId.substring(1) + ".xml#"
				+ parentRefId;
	}
	
	
	
	@Override
	public PresentationHierarchy apply(Operator<? extends RpdComponent> anOperator) {
		return (PresentationHierarchy)anOperator.operate(this);
	}
	
	@Override
	public PresentationHierarchy applyWithInput(InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> dto) {
		return (PresentationHierarchy)anOperator.operate(this,dto);
	}


	@Override
	public String toString() {
		return "  Presentation Hierarchy:" + this.getXudmlObject().getName();
	}


}
