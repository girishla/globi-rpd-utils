package com.globi.rpd.xudml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;

import lombok.Data;

@Data
public class XudmlFolder {

	private List<Resource> resources;

	public XudmlFolder(String uri) {

		try {
			this.resources = Arrays.asList(ResourceFactory.loadResources( "file:" + uri + "/*.xml"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error during reading of folder " + uri);

		}

	}

	public void deleteFileFromUri(String resourceUri) {

		Path p = Paths.get(resourceUri);
		p.toFile()
				.delete();

	}
	
	
	public List<File> getFiles(){
		
		return this.resources
		.stream()
		.map(resource -> {
			try {
				return resource.getFile();
			} catch (IOException e) {

				e.printStackTrace();
				throw new RuntimeException("Unexpected IO Exception. Aborting Operation.");
			}
		})
		.collect(Collectors.toList());

		
	}
	

	public enum FolderType {
		CATALOG, MODEL, PHYSICAL,PRESENTATIONTABLE,LOGICALTABLE;
	}

}
