package com.globi.rpd.xudml;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.Resource;

import lombok.Data;

@Data
public class XudmlFolder {
	
	private List<Resource> resources;
	
	public XudmlFolder(String uri) throws IOException{
		
		this.resources=Arrays.asList(ResourceFactory.loadResources(uri));
		
	}

}
