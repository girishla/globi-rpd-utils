package com.globi.rpd.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class RpdCommand {

	@SuppressWarnings("unchecked")
	@ShellMethod("Update Presentation Catalog.")
	public boolean updatePresentationCatalog(String strategyName,String subjectAreaName) {

		Class<?> strategyClass = null;
		try {
			strategyClass = Class.forName("com.globi.rpd.cli." + strategyName);
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found: " + strategyName );
			return false;
		}

		// Instantiate the strategy
		RpdObjectCommand<Boolean, String> strategy = null;
		try {
			strategy = (RpdObjectCommand<Boolean, String>) strategyClass.newInstance();
		} catch (IllegalAccessException e) {
			System.err.println("Class not accessible: "+ strategyName );
		} catch (InstantiationException e) {
			System.err.println("Class not instantiable: "+ strategyName );
		}

		return strategy.execute(subjectAreaName);
	}

}
