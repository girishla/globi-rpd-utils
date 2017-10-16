package com.globi.rpd.xudml;

import java.io.IOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

public class ResourceFactory {

	//TODO: change to bean
	public static Resource fromURL(String url) {

		ResourceLoader loader = new DefaultResourceLoader();
		return loader.getResource(url);

		
	}
	
    public static Resource[]  loadResources(String pattern) throws IOException {
    	ResourceLoader loader = new DefaultResourceLoader();
        return ResourcePatternUtils.getResourcePatternResolver(loader).getResources(pattern);
    }

}
