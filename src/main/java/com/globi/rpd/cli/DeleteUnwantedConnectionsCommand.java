package com.globi.rpd.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.globi.rpd.dsl.RpdBuilderFactory;


@ShellComponent
public class DeleteUnwantedConnectionsCommand {


	@ShellMethod(value = "Delete unwanted Subject Areas")
	public String deleteBadConnections(String basepath) throws Exception {

		try {

			RpdBuilderFactory.newBuilder()
					.init()
					.setRepoPath(basepath)
					.noInputs()
					.loadDatabase()
					.deleteDatabase(database -> database.getName()
							.equals("1 - DELETE (Use for Import)"))
					.noMoreWork()
					.save(basepath)
					.get();

		} catch (Exception e) {
			e.printStackTrace();
			return "COMMAND FAILED: " + e.getMessage();
		}

		return "COMMAND PROCESSED.";

	}

}
