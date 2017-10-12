package com.globi;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.Marshaller;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;


public enum MarshallerConfig {
	INSTANCE;

	public Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setPackagesToScan("xudml");
		Map<String, Object> marshallerProps = new HashMap<String, Object>();
		marshallerProps.put("jaxb.formatted.output", true);
		
		marshallerProps.put(Marshaller.JAXB_FRAGMENT, false);
//		marshallerProps.put("com.sun.xml.bind.xmlHeaders","<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		marshaller.setMarshallerProperties(marshallerProps);
//		marshaller.setSupportDtd(true);
		return marshaller;
	}
}