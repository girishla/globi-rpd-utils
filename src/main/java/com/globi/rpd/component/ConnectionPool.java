

package com.globi.rpd.component;

import com.globi.rpd.AppProperties;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;
import com.globi.rpd.xudml.XudmlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.ConnectionPoolW;

@Data
@EqualsAndHashCode(callSuper=true)
public class ConnectionPool extends MarshalledRpdComponent<ConnectionPoolW> implements Operable<RpdComponent>{

	
	/**
	 * Represents a FULL reference to the {@link ConnectionPool} in the
	 * format {@code parentDatabaseId - cpId }. This is then used to parse and
	 * derive all the other ref related fields
	 * example: m40000456-6dc5-167d-806e-c0a838100000-m40000457-6dc5-167d-806e-c0a838100000
	 */
	private String refId;

	/**
	 * path reference to the table
	 * example: oracle/bi/server/base/ConnectionPool/40000568-6dc5-167d-806e-c0a838100000.xml#m40000568-6dc5-167d-806e-c0a838100000
	 */
	private String ref;
	private String parentRefId;
	private String parentRef;

	public ConnectionPool(String id) {
		super(id);
		this.setResourceUri(AppProperties.INSTANCE.getBasePath()+ "/oracle/bi/server/base/ConnectionPool/" + id + ".xml");

	}
	
	public static ConnectionPool fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		ConnectionPool newConnectionPool=new ConnectionPool(id);
		newConnectionPool.setResourceUri(resourceUri);
		return newConnectionPool;
	}

	
	public static ConnectionPool fromRef(String refId) {
	
		
		ConnectionPool newConnectionPool=new ConnectionPool(refId.split("-m")[1]);
		
		newConnectionPool.refId = refId;
		newConnectionPool.setId(refId.split("-m")[1]);
		newConnectionPool.setResourceUri(AppProperties.INSTANCE.getBasePath() + XudmlConstants.XUDML_CONNECTIONPOOLURL + newConnectionPool.getId() + ".xml");
		newConnectionPool.ref =  XudmlConstants.XUDML_CONNECTIONPOOLURL  + newConnectionPool.getId()  + ".xml#m" + newConnectionPool.getId() ;
		newConnectionPool.parentRefId = refId.split("-m")[0];
		newConnectionPool.parentRef = XudmlConstants.XUDML_DATABASEURL + newConnectionPool.parentRefId.substring(1) + ".xml#"
				+ newConnectionPool.parentRefId;
		
		return newConnectionPool;
		
	}
	
	
	@Override
	public ConnectionPool apply(Operator<? extends RpdComponent> anOperator) {
		return (ConnectionPool)anOperator.operate(this);
	}

	@Override
	public String toString() {
		return "  ConnectionPool:" + this.getXudmlObject().getName();
	}

}

