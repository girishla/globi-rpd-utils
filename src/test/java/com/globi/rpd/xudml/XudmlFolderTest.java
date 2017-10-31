package com.globi.rpd.xudml;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.stream.Collectors;

import org.junit.Test;

public class XudmlFolderTest {

	
	@Test
	public void canLoadAllXudmlFilesFromLocation() throws IOException {

		XudmlFolder folder = new XudmlFolder("testrepo/oracle/bi/server/base/PresentationCatalog");

		assertThat(folder.getResources().stream().map(resource -> resource.getFilename()).collect(Collectors.toList()))
				.contains("40000456-6dc5-167d-806e-c0a838100000.xml");
		

	}
	
	
}
