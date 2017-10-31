package com.globi.rpd;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultLoggerProgressMonitor implements TraversingOperatorProgressMonitor {
	

	@Override
	public void operated(String operationName, Object aOperable) {
//		log.info("-----------------------------------");
//		log.info(operationName + "::" + aOperable.toString());
//		log.info("-----------------------------------");
	}

	@Override
	public void traversed(String operationName, Object aOperable) {
//		log.info("-----------------------------------");
//		log.info(operationName + "::" + aOperable.toString());
//		log.info("-----------------------------------");
	}

}
