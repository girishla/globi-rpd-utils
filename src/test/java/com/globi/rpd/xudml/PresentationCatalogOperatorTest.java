package com.globi.rpd.xudml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.junit.Test;

import com.globi.rpd.DefaultLoggerProgressMonitor;
import com.globi.rpd.component.PresentationCatalog;
import com.globi.rpd.operator.BreadthFirstTraversingOperator;
import com.globi.rpd.operator.HydratingOperator;
import com.globi.rpd.operator.XudmlMarshallingOperator;
import com.globi.rpd.operator.XudmlUnmarshallingOperator;
import com.globi.rpd.traverser.DefaultTraverser;

public class PresentationCatalogOperatorTest {

	@Test
	public void unmarshalsPresentationCatalogXudmlFile() throws Exception {

		PresentationCatalog presCatalog = PresentationCatalog.fromResource(
				"testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");
		XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
		BreadthFirstTraversingOperator traversingUnmarshaller = new BreadthFirstTraversingOperator(new DefaultTraverser(),
				unmarshalOperator);
		presCatalog.apply(traversingUnmarshaller);
		assertThat(presCatalog.getXudmlObject()
				.getName()).isEqualTo("Global Reporting - Measures - Performance Metrics");
	}

	@Test
	public void CanHydratePresentationtablesFromXudmlFile() throws Exception {

		PresentationCatalog presCatalog = PresentationCatalog.fromResource(
				"testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");
		XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
		BreadthFirstTraversingOperator traversingUnmarshaller = new BreadthFirstTraversingOperator(new DefaultTraverser(),
				unmarshalOperator);
		traversingUnmarshaller.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(traversingUnmarshaller);
		HydratingOperator hydratingOperator = new HydratingOperator();
		BreadthFirstTraversingOperator traversingHydrator = new BreadthFirstTraversingOperator(new DefaultTraverser(),
				hydratingOperator);
		presCatalog.apply(traversingHydrator);
		assertThat(presCatalog.getPresentationTables()
				.size()).isEqualTo(4);

		
	}

	@Test
	public void CanSavePresentationCatalogFromXudmlFile() throws Exception {

		String outFile = "testprescat_OUT.xml";

		// delete output file if it exists
		File file = new File(XudmlConstants.TEMP_DIR, outFile);
		if (file.exists()) {
			file.delete();
		}

		PresentationCatalog presCatalog = PresentationCatalog.fromResource(
				"testrepo/oracle/bi/server/base/PresentationCatalog/40000456-6dc5-167d-806e-c0a838100000.xml");

		XudmlUnmarshallingOperator unmarshalOperator = new XudmlUnmarshallingOperator();
		BreadthFirstTraversingOperator tv = new BreadthFirstTraversingOperator(new DefaultTraverser(), unmarshalOperator);
		tv.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(tv);

		HydratingOperator hydratingOperator = new HydratingOperator();
		BreadthFirstTraversingOperator tv2 = new BreadthFirstTraversingOperator(new DefaultTraverser(), hydratingOperator);
		tv2.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(tv2);

		presCatalog.setResourceUri(XudmlConstants.TEMP_DIR + outFile);

		XudmlMarshallingOperator marshallingOperator = new XudmlMarshallingOperator();
		BreadthFirstTraversingOperator tv3 = new BreadthFirstTraversingOperator(new DefaultTraverser(),
				marshallingOperator);
		tv3.setProgressMonitor(new DefaultLoggerProgressMonitor());
		presCatalog.apply(tv3);

		assertThat(presCatalog.getPresentationTables()
				.size()).isEqualTo(4);

	}

}
