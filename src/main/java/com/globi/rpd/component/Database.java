
package com.globi.rpd.component;

import java.util.ArrayList;
import java.util.List;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.InputOperator;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import xudml.DatabaseW;

@Data
@EqualsAndHashCode(callSuper=true)
public class Database extends MarshalledRpdComponent<DatabaseW> implements Operable<RpdComponent>{

	private final List<ConnectionPool> connectionPools = new ArrayList<ConnectionPool>();
	private final List<Schema> schemas = new ArrayList<Schema>();

	public Database(String id) {
		super(id);
	}
	
	public static Database fromResource(String resourceUri) {
		String id=resourceUri.substring(resourceUri.lastIndexOf("\\")+1).replace(".xml", "");
		Database newDatabase=new Database(id);
		newDatabase.setResourceUri(resourceUri);
		return newDatabase;
	}

	
	@Override
	public Database apply(Operator<? extends RpdComponent> anOperator) {
		return (Database)anOperator.operate(this);
	}
	
	
	@Override
	public Database applyWithInput(InputOperator<? extends RpdComponent> anOperator,TableColumnMetadataDTO dto) {
		return (Database)anOperator.operate(this,dto);
	}


	@Override
	public String toString() {
		return "  Database:" + this.getXudmlObject().getName();
	}


}

