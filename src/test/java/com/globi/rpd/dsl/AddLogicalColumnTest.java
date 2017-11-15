package com.globi.rpd.dsl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.springframework.util.FileSystemUtils;

import com.globi.rpd.TableColumnDtoObjectMother;
import com.globi.rpd.operator.AddColumnOperator;
import com.globi.rpd.xudml.XudmlConstants;

public class AddLogicalColumnTest {

	@Test
	public void canChangePhysicalLayerOfRpdWithFluentSyntax() throws IOException {
		
		FileSystemUtils.deleteRecursively(new File(XudmlConstants.XUDML_COPYURL));
		FileSystemUtils.copyRecursively(new File(XudmlConstants.XUDML_BASEURL), new File(XudmlConstants.XUDML_COPYURL));

		RpdBuilderFactory.newBuilder()
				.init()
				.setRepoPath(XudmlConstants.XUDML_COPYURL)
				.setInput(Arrays.asList(TableColumnDtoObjectMother.getExampleDimLogicalColumn()))
				.loadDatabase()
//				.loadModel()
//				.loadCatalog()
				.applyInputOperatorToRpd(AddColumnOperator.class)
				.noMoreWork()
//				.nothingToSave()
				.save(XudmlConstants.XUDML_COPYURL)
				.get(); 

	}

}


