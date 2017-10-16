//package com.globi.rpd.cli;
//
//import java.io.IOException;
//
//import org.springframework.core.io.Resource;
//import org.springframework.shell.core.CommandMarker;
//import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
//import org.springframework.shell.core.annotation.CliCommand;
//import org.springframework.shell.core.annotation.CliOption;
//import org.springframework.stereotype.Component;
//
//import com.globi.rpd.xudml.XudmlFolder;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//public class RpdCliCommands implements CommandMarker {
//
//	@CliAvailabilityIndicator({ "globiutils arrangePresentationCatalog" })
//	public boolean isCommandAvailable() {
//		return true;
//	}
//
//	@CliCommand(value = "globiutils arrangePresentationCatalog", help = "Rename and Rearrange presentation catalog contents to Globi development standards")
//	public String arrangePresentationCatalog(
//			@CliOption(key = {
//					"rpdlocation" }, mandatory = true, help = "Full path to the directory where the MDS XML RPD is stored") final String location)
//			throws IOException {
//
//		XudmlFolder folder = new XudmlFolder(location + "/oracle/bi/server/base/PresentationTable" + "/*.xml");
//		
//		for (Resource resource : folder.getResources()) {
//
//			log.debug(resource.getFilename());
//
//		}
//
//		return "Finished Processing";
//
//	}
//
//}
