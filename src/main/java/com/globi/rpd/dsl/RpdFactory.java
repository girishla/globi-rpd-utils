package com.globi.rpd.dsl;


/**
 * The central factory for fluent {@link StandardRpdBuilder} API.
 * @author Girish Lakshmanan
 *
 */
public class RpdFactory {
	
	public static StandardRpdBuilder from(String xudmlDirectory) {
		return new StandardRpdBuilder();
	}
	
	

}
