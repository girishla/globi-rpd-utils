package com.globi.rpd.dsl;


/**
 * The central factory for fluent {@link RpdBuilder} API.
 * @author Girish Lakshmanan
 *
 */
public class RpdFactory {
	
	public static RpdBuilder from(String xudmlDirectory) {
		return new RpdBuilder();
	}
	
	

}
