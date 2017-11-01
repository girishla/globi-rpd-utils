package com.globi.rpd.dsl;

import org.junit.Test;

import com.globi.rpd.xudml.XudmlConstants;

public class BasicDslTest {
	
	
	
//	 @Bean
//	  public IntegrationFlow fileReadingFlow() {
//	      return IntegrationFlows
//	             .from(s -> s.file(tmpDir.getRoot()), e -> e.poller(Pollers.fixedDelay(100)))
//	             .transform(Transformers.fileToString())
//	             .channel(MessageChannels.queue("fileReadingResultChannel"))
//	             .get();
//	  }

	
	@Test
	public void canBuildRpdWithFluentSyntax(){
		RpdFactory.from(XudmlConstants.XUDML_COPYURL);
	}
	
	
}
