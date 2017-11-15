
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
import xudml.SchemaW;

@Data
@EqualsAndHashCode(callSuper=true)
public class Schema extends MarshalledRpdComponent<SchemaW> implements Operable<RpdComponent>{


	/**
	 * Represents a FULL reference to the {@link Schema} in the
	 * format {@code parentDatabaseId - schemaId }. This is then used to parse and
	 * derive all the other ref related fields
	 * example: m40000456-6dc5-167d-806e-c0a838100000-m40000457-6dc5-167d-806e-c0a838100000
	 */
	private String refId;

	/**
	 * path reference to the table
	 * example: oracle/bi/server/base/Schema/40000568-6dc5-167d-806e-c0a838100000.xml#m40000568-6dc5-167d-806e-c0a838100000
	 */
	private String ref;
	private String parentRefId;
	private String parentRef;
	
	private final List<PhysicalTable> physicalTables = new ArrayList<>();
	

	public Schema(String id) {
		super(id);
		this.setResourceUri(AppProperties.INSTANCE.getBasePath()+ "/oracle/bi/server/base/Schema/" + id + ".xml");
	}
	
	public static Schema fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		Schema newSchema=new Schema(id);
		newSchema.setResourceUri(resourceUri);
		return newSchema;
	}

	
	public static Schema fromRef(String refId) {
	
		
		Schema newSchema=new Schema(refId.split("-m")[1]);
		
		newSchema.refId = refId;
		newSchema.setId(refId.split("-m")[1]);
		newSchema.setResourceUri(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_SCHEMAURL + newSchema.getId() + ".xml");
		newSchema.ref =  XudmlConstants.XUDML_SCHEMAURL  + newSchema.getId()  + ".xml#m" + newSchema.getId() ;
		newSchema.parentRefId = refId.split("-m")[0];
		newSchema.parentRef = XudmlConstants.XUDML_DATABASEURL + newSchema.parentRefId.substring(1) + ".xml#"
				+ newSchema.parentRefId;
		
		return newSchema;
		
	}
	
	
	@Override
	public Schema apply(Operator<? extends RpdComponent> anOperator) {
		return (Schema)anOperator.operate(this);
	}
	
	@Override
	public Schema applyWithInput(InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> dto) {
		return (Schema)anOperator.operate(this,dto);
	}


	@Override
	public String toString() {
		return "  Schema:" + this.getXudmlObject().getName();
	}


}

