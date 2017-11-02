package com.globi.rpd.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ShellComponent
public class RpdCommand {

	@SuppressWarnings("unchecked")
	@ShellMethod("Run actions based on the input strategy class name")
	public String run(String strategyName,String subjectAreaName) throws Exception {

		Class<?> strategyClass = null;
		try {
			strategyClass = Class.forName("com.globi.rpd.cli." + strategyName);
		} catch (ClassNotFoundException e) {
			log.error("Class not found: " + strategyName );
			return "COMMAND FAILED. ";
		}

		// Instantiate the strategy
		RpdObjectCommand<Boolean, String> strategy = null;
		try {
			strategy = (RpdObjectCommand<Boolean, String>) strategyClass.newInstance();
		} catch (IllegalAccessException e) {
			log.error("Class not accessible: "+ strategyName);
		} catch (InstantiationException e) {
			log.error("Class not instantiable: "+ strategyName );
		}

		return strategy.execute(subjectAreaName);
	}

}
