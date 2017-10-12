package com.globi.rpd.xudml;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceFactory {

	//TODO: change to bean
	public static Resource fromURL(String url) {

		ResourceLoader loader = new DefaultResourceLoader();
		return loader.getResource(url);

		
	}

}
