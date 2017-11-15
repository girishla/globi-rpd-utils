package com.globi.rpd.dsl;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import com.globi.rpd.xudml.XudmlConstants;

public class PhysicalLayerDslTest {

	@Test
	public void canChangePhysicalLayerOfRpdWithFluentSyntax() throws IOException {
		
		FileSystemUtils.deleteRecursively(new File(XudmlConstants.XUDML_COPYURL));
		FileSystemUtils.copyRecursively(new File(XudmlConstants.XUDML_BASEURL), new File(XudmlConstants.XUDML_COPYURL));

		RpdBuilderFactory.newBuilder()
				.init()
				.setRepoPath(XudmlConstants.XUDML_COPYURL)
				.noInputs()
				.loadDatabase()
				.noMoreWork()
				.nothingToSave()
				.get(); 

	}

}
