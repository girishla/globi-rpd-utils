package com.globi.rpd.dsl;

import java.io.IOException;

import org.junit.Test;

import com.globi.rpd.operator.SubjectAreaGeneratorOperator;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

public class BasicDslTest {

	public static final String catalogPath = "file:\\" + XudmlConstants.XUDML_BASEURL + XudmlConstants.XUDML_CATALOGURL;
	public static final String modelPath = "file:\\" + XudmlConstants.XUDML_BASEURL + XudmlConstants.XUDML_MODELURL;

	@Test
	public void canBuildRpdWithFluentSyntax() throws IOException {
		RpdFactory.newBuilder()
				.init()
				.catalog(new XudmlFolder(catalogPath))
				.model(new XudmlFolder(modelPath))
				.applyModelOperator(SubjectAreaGeneratorOperator.class, model -> model.getName()
						.equals("Global Reporting"))
				.noMoreWork()
				.save()
				.get();

	}

}
