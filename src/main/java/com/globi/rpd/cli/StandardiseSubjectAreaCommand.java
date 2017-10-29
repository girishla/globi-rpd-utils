package com.globi.rpd.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class StandardiseSubjectAreaCommand {
	 @ShellMethod("Add two integers together.")
	    public int add(int a, int b) {
	        return a + b;
	    }
	
	
}





