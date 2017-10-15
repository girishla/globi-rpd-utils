package com.globi.rpd.xudml;


import org.junit.Test;
import org.junit.Assert;




import com.globi.HydratingVisitor;
import com.globi.PresentationCatalogTraverser;
import com.globi.TraversingVisitor;
import com.globi.XudmlUnmarshallingVisitor;
import com.globi.rpd.presentationcatalog.PresentationCatalog;

import lombok.extern.slf4j.Slf4j;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class XudmlUnmarshallerTest {

	//Resource resource=resourceLoader.getResource("C:\\working\\obiee\\globi\\RPD\\oracle\\bi\\server\\base\\PresentationCatalog\\00000e87-3a9a-1677-806e-0ab93cb00000.xml");
	
//	private static final Resource resource =ResourceFactory.fromURL(XudmlConstants.XUDML_BASEURL + "/oracle/bi/server/base/PresentationCatalog/8000009a-e46e-1677-806e-0a3fcf1d0000.xml"); 
			
	//new ClassPathResource("xmls/testprescat.xml");
	
	
	@Test
	public void unmarshalsPresentationCatalogXudmlFile() throws Exception{

		PresentationCatalog presCatalog=new PresentationCatalog(XudmlConstants.XUDML_BASEURL + "/oracle/bi/server/base/PresentationCatalog/00000d3f-3a9a-1677-806e-0ab93cb00000.xml");

		   XudmlUnmarshallingVisitor unmarshalViz = new XudmlUnmarshallingVisitor();
	       TraversingVisitor<Object,Exception> tv = new TraversingVisitor<>(
	                new PresentationCatalogTraverser<Exception>(), unmarshalViz);
	        tv.setTraverseFirst(false);
	        presCatalog.accept(tv);
	
	        HydratingVisitor hydratingViz = new HydratingVisitor();
		       TraversingVisitor<Object,Exception> tv2 = new TraversingVisitor<>(
		                new PresentationCatalogTraverser<Exception>(), hydratingViz);
		        tv2.setTraverseFirst(false);
		        presCatalog.accept(tv2);
		
	
		        assertThat(presCatalog.getXudmlObject().getName()).isEqualTo("Global Reporting - Measures - Entitlement");
		
		
		
	}

	
}
