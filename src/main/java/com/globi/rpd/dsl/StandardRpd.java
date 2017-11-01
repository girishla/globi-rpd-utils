package com.globi.rpd.dsl;

import java.util.Set;

import com.globi.rpd.component.BusinessModel;
import com.globi.rpd.component.Database;
import com.globi.rpd.component.PresentationCatalog;

import lombok.Data;

/**
 * Container for RPD Component Objects
 * @author Girish Lakshmanan
 *
 */
@Data
public class StandardRpd implements Rpd {

	
	private final Set<PresentationCatalog> catalogObjects;
	private final Set<BusinessModel> modelObjects;
	private final Set<Database> physicalObjects;

	public StandardRpd(Set<PresentationCatalog> catalogObjects, Set<BusinessModel> modelObjects, Set<Database> physicalObjects) {
		this.catalogObjects = catalogObjects;
		this.modelObjects=modelObjects;
		this.physicalObjects=physicalObjects;
	}
	

	
}
