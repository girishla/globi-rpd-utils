package com.globi.rpd.dsl;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import com.globi.rpd.operator.SortingOperator;
import com.globi.rpd.operator.SubjectAreaGeneratorOperator;
import com.globi.rpd.xudml.XudmlConstants;

public class BasicDslTest {

	@Test
	public void canBuildRpdWithFluentSyntax() throws IOException {
		
		FileSystemUtils.deleteRecursively(new File(XudmlConstants.XUDML_COPYURL));
		FileSystemUtils.copyRecursively(new File(XudmlConstants.XUDML_BASEURL), new File(XudmlConstants.XUDML_COPYURL));

		RpdBuilderFactory.newBuilder()
				.init()
				.setRepoPath(XudmlConstants.XUDML_COPYURL)
				.noInputs()
				.loadCatalog()
				.loadModel()
				.applyOperatorToRpd(SubjectAreaGeneratorOperator.class)
				.applyOperatorToAllCatalogs(SortingOperator.class)
				.noMoreWork()
				.save(XudmlConstants.XUDML_COPYURL)
				.get(); 

	}

}
