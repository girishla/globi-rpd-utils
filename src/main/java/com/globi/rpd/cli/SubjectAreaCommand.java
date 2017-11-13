package com.globi.rpd.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ShellComponent
public class SubjectAreaCommand {

	@SuppressWarnings("unchecked")
	@ShellMethod(value="Run actions based on the input strategy class name")
	public String generateSubjectArea(String strategyName,String subjectAreaName,String basepath) throws Exception{

		
		Class<?> strategyClass = null;
		
		
		
		try {
			strategyClass = Class.forName("com.globi.rpd.cli." + strategyName);
		} catch (ClassNotFoundException e) {
			log.error("Class not found: " + strategyName );
			return "COMMAND FAILED. ";
		}

		// Instantiate the strategy
		RpdCommandExecutor<Boolean, SubjectAreaGeneratorInput> strategy = null;
		try {
			strategy = (RpdCommandExecutor<Boolean, SubjectAreaGeneratorInput>) strategyClass.newInstance();
		} catch (IllegalAccessException e) {
			log.error("Class not accessible: "+ strategyName);
		} catch (InstantiationException e) {
			log.error("Class not instantiable: "+ strategyName );
		}

		return strategy.execute(new SubjectAreaGeneratorInput(basepath,subjectAreaName));
		
		
	}
	
	
	 @ShellMethod(value = "Add numbers.", key = "sum")
     public int add(int a, int b) {
             return a + b;
     }

}
