package com.globi.rpd.dsl;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import com.globi.rpd.operator.SortingOperator;
import com.globi.rpd.operator.SubjectAreaGeneratorOperator;
import com.globi.rpd.xudml.XudmlConstants;
import com.globi.rpd.xudml.XudmlFolder;

public class BasicDslTest {
	public static final String catalogPath =  XudmlConstants.XUDML_COPYURL + XudmlConstants.XUDML_CATALOGURL;
	public static final String modelPath =  XudmlConstants.XUDML_COPYURL + XudmlConstants.XUDML_MODELURL;
	
	
	@Test
	public void canBuildRpdWithFluentSyntax() throws IOException {
		
		FileSystemUtils.deleteRecursively(new File(XudmlConstants.XUDML_COPYURL));
		FileSystemUtils.copyRecursively(new File(XudmlConstants.XUDML_BASEURL), new File(XudmlConstants.XUDML_COPYURL));

		RpdFactory.newBuilder()
				.init()
				.setRepoPath(XudmlConstants.XUDML_COPYURL)
				.catalog(new XudmlFolder(catalogPath))
				.model(new XudmlFolder(modelPath))
				.applyRpdOperator(SubjectAreaGeneratorOperator.class)
				.applyOperatorToAllCatalogs(SortingOperator.class)
				.noMoreWork()
				.save(XudmlConstants.XUDML_COPYURL)
				.get();

	}

}
