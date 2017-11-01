package com.globi.rpd.dsl;

import java.io.IOException;

import org.junit.Test;

import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

public class BasicDslTest {

	public static final String catalogPath = "file:\\" + XudmlConstants.XUDML_BASEURL + XudmlConstants.XUDML_CATALOGURL;

	@Test
	public void canBuildRpdWithFluentSyntax() throws IOException {
		RpdFactory.newBuilder()
				.init()
				.catalog(new XudmlFolder(catalogPath))
				.noMoreCatalogs()
				.get();

	}

}
