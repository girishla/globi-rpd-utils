package com.globi.rpd.xudml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.stream.StreamSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.FileCopyUtils;

import com.globi.MarshallerConfig;

import lombok.extern.slf4j.Slf4j;
import xudml.PresentationCatalogT;
import xudml.PresentationCatalogW;

@Slf4j
public class RPDUnmarshaller {

	PresentationCatalogT presCatalog;
	private static final Jaxb2Marshaller marshaller=MarshallerConfig.INSTANCE.jaxb2Marshaller();
	
	public void unmarshallRPD() throws XmlMappingException, IOException{
		
		
		InputStream is = null;

		try {
			Resource resource = new ClassPathResource("xmls/testprescat.xml");
			is = resource.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FileCopyUtils.copy(is, baos);

			JAXBElement<PresentationCatalogW> root =(JAXBElement<PresentationCatalogW>) marshaller.unmarshal(new StreamSource(new StringReader((baos.toString("UTF-8")))));
			
			this.presCatalog =root.getValue();
			
			log.debug(this.presCatalog.getName());
			log.debug(this.presCatalog.getRefTables().getRefPresentationTable().get(0).getPresentationTableRef());
			
			
		} finally {
			if (is != null) {
				is.close();
			}
		}
		
		
	}
	
}
