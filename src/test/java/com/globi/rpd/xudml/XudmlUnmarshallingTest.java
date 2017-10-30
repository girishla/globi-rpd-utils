package com.globi.rpd.xudml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.core.io.Resource;

import com.globi.HydratingOperator;
import com.globi.PresentationCatalogTraverser;
import com.globi.TraversingOperator;
import com.globi.XudmlMarshallingOperator;
import com.globi.XudmlUnmarshallingOperator;
import com.globi.rpd.presentationcatalog.PresentationCatalog;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XudmlUnmarshallingTest {

	@Test
	public void unmarshalsPresentationCatalogXudmlFile() throws Exception {

		PresentationCatalog presCatalog = new PresentationCatalog("testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");

		XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
		TraversingOperator<Object, Exception> traversingUnmarshaller = new TraversingOperator<>(new PresentationCatalogTraverser<Exception>(),
				unmarshalOperator);

		presCatalog.apply(traversingUnmarshaller);

		assertThat(presCatalog.getXudmlObject().getName()).isEqualTo("Global Reporting - Measures - Performance Metrics");

	}

	@Test
	public void CanHydratePresentationtablesFromXudmlFile() throws Exception {

		PresentationCatalog presCatalog = new PresentationCatalog("testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");

		XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
		TraversingOperator<Object, Exception> traversingUnmarshaller = new TraversingOperator<>(new PresentationCatalogTraverser<Exception>(),
				unmarshalOperator);

		presCatalog.apply(traversingUnmarshaller);

		HydratingOperator hydratingOperator = new HydratingOperator();
		TraversingOperator<Object, Exception> traversingHydrator = new TraversingOperator<>(
				new PresentationCatalogTraverser<Exception>(), hydratingOperator);

		presCatalog.apply(traversingHydrator);

		assertThat(presCatalog.getPresentationTables().size()).isEqualTo(4);

	}

	@Test
	public void CanSavePresentationCatalogFromXudmlFile() throws Exception {

		String outFile = "testprescat_OUT.xml";

		// delete output file if it exists
		File file = new File(XudmlConstants.TEMP_DIR, outFile);
		if (file.exists()) {
			file.delete();
		}

		PresentationCatalog presCatalog = new PresentationCatalog("testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");

		XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
		TraversingOperator<Object, Exception> tv = new TraversingOperator<>(new PresentationCatalogTraverser<Exception>(),
				unmarshalOperator);
		presCatalog.apply(tv);

		HydratingOperator hydratingOperator = new HydratingOperator();
		TraversingOperator<Object, Exception> tv2 = new TraversingOperator<>(
				new PresentationCatalogTraverser<Exception>(), hydratingOperator);
		presCatalog.apply(tv2);

		presCatalog.setResourceUri(XudmlConstants.TEMP_DIR + outFile);

		XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
		TraversingOperator<Object, Exception> tv3 = new TraversingOperator<>(
				new PresentationCatalogTraverser<Exception>(), marshallingOperator);
		tv3.setTraverseFirst(false);
		presCatalog.apply(tv3);

		assertThat(presCatalog.getPresentationTables().size()).isEqualTo(4);

	}

	@Test
	public void canLoadAllXudmlFilesFromLocation() throws IOException {

		XudmlFolder folder = new XudmlFolder("testrepo/oracle/bi/server/base/PresentationCatalog");

		assertThat(folder.getResources().stream().map(resource -> resource.getFilename()).collect(Collectors.toList()))
				.contains("40000456-6dc5-167d-806e-c0a838100000.xml");

	}
	
	
	
	
	
	

}
