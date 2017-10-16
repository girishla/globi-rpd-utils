package com.globi.rpd.xudml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.core.io.Resource;

import com.globi.HydratingVisitor;
import com.globi.PresentationCatalogTraverser;
import com.globi.TraversingVisitor;
import com.globi.XudmlMarshallingVisitor;
import com.globi.XudmlUnmarshallingVisitor;
import com.globi.rpd.presentationcatalog.PresentationCatalog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XudmlUnmarshallerTest {

	// PresentationCatalog presCatalog = new
	// PresentationCatalog(XudmlConstants.XUDML_BASEURL
	// +
	// "/oracle/bi/server/base/PresentationCatalog/00000d3f-3a9a-1677-806e-0ab93cb00000.xml");

	@Test
	public void unmarshalsPresentationCatalogXudmlFile() throws Exception {

		PresentationCatalog presCatalog = new PresentationCatalog("./xmls/testprescat.xml");

		XudmlUnmarshallingVisitor unmarshalViz = new XudmlUnmarshallingVisitor();
		TraversingVisitor<Object, Exception> tv = new TraversingVisitor<>(new PresentationCatalogTraverser<Exception>(),
				unmarshalViz);
		tv.setTraverseFirst(false);
		presCatalog.accept(tv);

		assertThat(presCatalog.getXudmlObject().getName()).isEqualTo("Global Reporting - Measures - Entitlement");

	}

	@Test
	public void CanHydratePresentationtablesFromXudmlFile() throws Exception {

		PresentationCatalog presCatalog = new PresentationCatalog("./xmls/testprescat.xml");

		XudmlUnmarshallingVisitor unmarshalViz = new XudmlUnmarshallingVisitor();
		TraversingVisitor<Object, Exception> tv = new TraversingVisitor<>(new PresentationCatalogTraverser<Exception>(),
				unmarshalViz);
		tv.setTraverseFirst(false);
		presCatalog.accept(tv);

		HydratingVisitor hydratingViz = new HydratingVisitor();
		TraversingVisitor<Object, Exception> tv2 = new TraversingVisitor<>(
				new PresentationCatalogTraverser<Exception>(), hydratingViz);
		tv2.setTraverseFirst(false);
		presCatalog.accept(tv2);

		assertThat(presCatalog.getPresentationTables().size()).isEqualTo(12);

	}

	@Test
	public void CanSavePresentationCatalogFromXudmlFile() throws Exception {

		String outFile = "testprescat_OUT.xml";

		// delete output file if it exists
		File file = new File(XudmlConstants.TEMP_DIR, outFile);
		if (file.exists()) {
			file.delete();
		}

		PresentationCatalog presCatalog = new PresentationCatalog("./xmls/testprescat.xml");

		XudmlUnmarshallingVisitor unmarshalViz = new XudmlUnmarshallingVisitor();
		TraversingVisitor<Object, Exception> tv = new TraversingVisitor<>(new PresentationCatalogTraverser<Exception>(),
				unmarshalViz);
		tv.setTraverseFirst(false);
		presCatalog.accept(tv);

		HydratingVisitor hydratingViz = new HydratingVisitor();
		TraversingVisitor<Object, Exception> tv2 = new TraversingVisitor<>(
				new PresentationCatalogTraverser<Exception>(), hydratingViz);
		tv2.setTraverseFirst(false);
		presCatalog.accept(tv2);

		presCatalog.setResourceUri(XudmlConstants.TEMP_DIR + outFile);

		XudmlMarshallingVisitor marshallingViz = new XudmlMarshallingVisitor();
		TraversingVisitor<Object, Exception> tv3 = new TraversingVisitor<>(
				new PresentationCatalogTraverser<Exception>(), marshallingViz);
		tv3.setTraverseFirst(false);
		presCatalog.accept(tv3);

		assertThat(presCatalog.getPresentationTables().size()).isEqualTo(12);

	}

	@Test
	public void canLoadAllXudmlFilesFromLocation() throws IOException {

		
		//XudmlConstants.XUDML_BASEURL +   "/oracle/bi/server/base/PresentationTable"
		XudmlFolder folder = new XudmlFolder("./xmls/*.xml");

		// for (Resource resource : folder.getResources()) {
		//
		// log.debug(resource.getFilename());
		//
		// }

		assertThat(folder.getResources().stream().map(resource -> resource.getFilename()).collect(Collectors.toList()))
				.contains("testprescat.xml");

	}
	
	
	
	
	
	

}
