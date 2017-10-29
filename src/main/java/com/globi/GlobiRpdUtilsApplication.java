package com.globi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.SpringShellAutoConfiguration;
import org.springframework.shell.jline.DefaultShellApplicationRunner;

@SpringBootApplication
public class GlobiRpdUtilsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobiRpdUtilsApplication.class, args);
	}
}
