package com.globi.rpd.dsl;

import java.util.HashSet;
import java.util.Set;

import com.globi.rpd.component.PresentationCatalog;

/**
 * The builder implementation for the fluent method chain
 * 
 * @author Girish Lakshmanan
 *
 */
public class RpdBuilder {

	private StandardRpd rpd;
	private final Set<PresentationCatalog> catalogObjects = new HashSet<PresentationCatalog>();

	RpdBuilder() {
	}

	StandardRpd get() {
		if (this.rpd == null) {
			this.rpd = new StandardRpd(catalogObjects);

		}
		return this.rpd;
	}

}
