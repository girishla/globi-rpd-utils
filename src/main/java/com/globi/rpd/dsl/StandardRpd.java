package com.globi.rpd.dsl;

import java.util.Set;

import com.globi.rpd.component.PresentationCatalog;

/**
 * Container for RPD Component Objects
 * @author Girish Lakshmanan
 *
 */
public class StandardRpd implements Rpd {

	
	private final Set<PresentationCatalog> catalogObjects;

	public StandardRpd(Set<PresentationCatalog> catalogObjects) {
		this.catalogObjects = catalogObjects;
	}
	

	
}
