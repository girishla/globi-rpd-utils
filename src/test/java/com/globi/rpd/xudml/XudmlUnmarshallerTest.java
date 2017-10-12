package com.globi.rpd.xudml;


import static org.mockito.Matchers.isNotNull;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;
import xudml.PresentationCatalogT;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class XudmlUnmarshallerTest {

	//Resource resource=resourceLoader.getResource("C:\\working\\obiee\\globi\\RPD\\oracle\\bi\\server\\base\\PresentationCatalog\\00000e87-3a9a-1677-806e-0ab93cb00000.xml");
	
	private static final Resource resource =ResourceFactory.fromURL(XudmlConstants.XUDML_BASEURL + "/oracle/bi/server/base/PresentationCatalog/8000009a-e46e-1677-806e-0a3fcf1d0000.xml"); 
			
	//new ClassPathResource("xmls/testprescat.xml");
	
	
	@Test
	public void unmarshalsPresentationCatalogXudmlFile() throws Exception{

		PresentationCatalogT presCatalog;
		
		XudmlMarshaller<PresentationCatalogT> rpdu=new XudmlMarshaller<PresentationCatalogT>();
		presCatalog=rpdu.unmarshall(resource);
		
	
	//	assertThat(presCatalog.getName()).isEqualTo("Global Reporting - Measures - Pipeline - Order Line");
		
		
	}
	
	
	@Test
	public void canUnmarshalAllPresentationCatalogFiles() throws Exception{

		
		PresentationCatalog catalog= PresentationCatalog.fromFile(resource);
		
		
		
	}
	
}
