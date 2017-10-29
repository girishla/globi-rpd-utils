package com.globi.rpd.cli;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class CliConfig {
	

//	@Bean
//	public Shell shell(@Qualifier("main") ResultHandler resultHandler) {
//		return new Shell(resultHandler);
//	}

}
