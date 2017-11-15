package com.globi.rpd.component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.globi.rpd.TableColumnMetadataDTO;
import com.globi.rpd.operator.InputOperator;
import com.globi.rpd.operator.Operable;
import com.globi.rpd.operator.Operator;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Container for RPD Component Objects
 * @author Girish Lakshmanan
 *
 */
@Getter
@Setter
@Slf4j
public class StandardRpd implements Rpd,RpdComponent,Operable<RpdComponent> {

	private final String id= UUID.randomUUID().toString();
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
	
	
	@Override
	public RpdComponent applyWithInput(InputOperator<? extends RpdComponent> anOperator,List<TableColumnMetadataDTO> dto) {
	
		log.debug("(((((((((((((((((((((((((((((((((");
		log.debug("(((((((((((((((((((((((((((((((((");
		log.debug(this.physicalObjects.size() +  anOperator.getClass().getName());
		
		return (StandardRpd)anOperator.operate(this,dto);
	}
	

	
}
