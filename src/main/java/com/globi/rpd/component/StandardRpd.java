package com.globi.rpd.component;

import java.util.Set;

import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Getter;
import lombok.Setter;

/**
 * Container for RPD Component Objects
 * @author Girish Lakshmanan
 *
 */
@Getter
@Setter
public class StandardRpd implements Rpd,RpdComponent,Operable<RpdComponent> {

	private final String id="STANDARDRPD";
	private final String name="STANDARDRPD";
	
	
	private final Set<PresentationCatalog> catalogObjects;
	private final Set<BusinessModel> modelObjects;
	private final Set<Database> physicalObjects;

	public StandardRpd(Set<PresentationCatalog> catalogObjects, Set<BusinessModel> modelObjects, Set<Database> physicalObjects) {
		this.catalogObjects = catalogObjects;
		this.modelObjects=modelObjects;
		this.physicalObjects=physicalObjects;
	}

	@Override
	public RpdComponent apply(Operator<? extends RpdComponent> anOperator) {
		return (StandardRpd)anOperator.operate(this);

	}
	

	
}
