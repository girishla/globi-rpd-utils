package com.globi.rpd.cli;

import com.globi.rpd.dsl.RpdBuilderFactory;
import com.globi.rpd.operator.SortingOperator;
import com.globi.rpd.operator.SubjectAreaGeneratorOperator;



/**
 * Command Executor responsible for generating all subject areas from the BuinessModel
 * @author Girish Lakshmanan
 *
 */
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
					.noInputs()
					.loadCatalog()
					.loadModel()
					.applyOperatorToRpd(SubjectAreaGeneratorOperator.class)
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

}
