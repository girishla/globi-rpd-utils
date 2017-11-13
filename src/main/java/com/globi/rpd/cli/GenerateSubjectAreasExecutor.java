package com.globi.rpd.cli;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileSystemUtils;

import com.globi.rpd.dsl.RpdBuilderFactory;
import com.globi.rpd.operator.SortingOperator;
import com.globi.rpd.operator.SubjectAreaGeneratorOperator;
import com.globi.rpd.xudml.XudmlConstants;

public class GenerateSubjectAreasExecutor implements RpdCommandExecutor<Boolean, SubjectAreaGeneratorInput> {


	@Override
	public String execute(SubjectAreaGeneratorInput input) throws Exception {

		if (!input.getSubjectAreaName().toUpperCase()
				.equals("ALL"))
			throw new IllegalArgumentException("Invalid Argument " + input.getSubjectAreaName() + "; Only ALL is supported at the moment");

		try {

			RpdBuilderFactory.newBuilder()
					.init()
					.setRepoPath(input.basepath)
					.loadCatalog()
					.loadModel()
					.applyRpdOperator(SubjectAreaGeneratorOperator.class)
					.applyOperatorToAllCatalogs(SortingOperator.class)
					.noMoreWork()
					.save(input.basepath)
					.get();

		} catch (Exception e) {
			e.printStackTrace();
			return "COMMAND FAILED: " + e.getMessage();
		}

		return "COMMAND PROCESSED.";
	}

	public void canBuildRpdWithFluentSyntax() throws IOException {

		FileSystemUtils.deleteRecursively(new File(XudmlConstants.XUDML_COPYURL));
		FileSystemUtils.copyRecursively(new File(XudmlConstants.XUDML_BASEURL), new File(XudmlConstants.XUDML_COPYURL));

		RpdBuilderFactory.newBuilder()
				.init()
				.setRepoPath(XudmlConstants.XUDML_COPYURL)
				.loadCatalog()
				.loadModel()
				.applyRpdOperator(SubjectAreaGeneratorOperator.class)
				.applyOperatorToAllCatalogs(SortingOperator.class)
				.noMoreWork()
				.save(XudmlConstants.XUDML_COPYURL)
				.get();

	}

}
