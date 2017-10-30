package com.globi.rpd.xudml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.FileCopyUtils;

import com.globi.rpd.MarshallerConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XudmlMarshaller<T>  implements ResourceLoaderAware {

	private static final Jaxb2Marshaller marshaller=MarshallerConfig.INSTANCE.jaxb2Marshaller();
	
	
	private ResourceLoader resourceLoader;

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	public Resource getResource(String location){
		return resourceLoader.getResource(location);
	}
	
	private T unmarshallResource(Resource resource) throws XmlMappingException, IOException{
		
		
		InputStream is = null;

		try {
			
			is = resource.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FileCopyUtils.copy(is, baos);

			@SuppressWarnings("unchecked")
			JAXBElement<T> root =(JAXBElement<T>) marshaller.unmarshal(new StreamSource(new StringReader((baos.toString("UTF-8")))));
			
			
			return root.getValue();
			
			
			
		} finally {
			if (is != null) {
				is.close();
			}
		}
		
		
	}
	
	
	private boolean marshallResource(String uri,JAXBElement<?> jaxbElement) throws XmlMappingException, IOException{
		
		  FileOutputStream os = null;
		  
		  log.debug("^^^^^^^^^^^^^^^^^^^^^^^^^"+uri);
		  
		  File newFile = new File(uri);
		  
	        try {
	        	
	            if (!newFile.exists()) {
	            	newFile.createNewFile();
	            }
	            os = new FileOutputStream(newFile);
	            
	            XudmlMarshaller.marshaller.marshal(jaxbElement, new StreamResult(os));
	            return true;
	        } finally {
	            if (os != null) {
	                os.close();
	            }
	        }

		
		
	}
	
	
	public boolean marshall(String uri,JAXBElement<?> jaxbElement){
		
		
		try {
			return	this.marshallResource(uri,jaxbElement);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error during marshalling of file: " + uri);
		}
		
		
	} 
	
	
	public T unmarshall(Resource resource){
		
		
		try {
			return	this.unmarshallResource(resource);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error during unmarshalling of file: " + resource.getFilename());
		}
		
		
	}
	
}
