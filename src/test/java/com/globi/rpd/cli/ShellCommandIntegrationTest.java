package com.globi.rpd.cli;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Input;
import org.springframework.shell.Shell;
import org.springframework.shell.result.DefaultResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes =CliConfig.class)
public class ShellCommandIntegrationTest {

	@Autowired
	private Shell shell;
	
	@Test
	public void runTest(){
		
		
		Object result=shell.evaluate(new Input(){
			@Override
			public String rawText() {
				return "add 1 3";
			}
			
		});

		DefaultResultHandler  resulthandler=new DefaultResultHandler();
		resulthandler.handleResult(result);
		
		
	}
	
	
}


