package com.globi.rpd.xudml;

import org.springframework.core.io.Resource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import xudml.PresentationTableT;

@Data
@Slf4j
public class PresentationTable {

	private PresentationTableT xudmlObject;

	public static PresentationTable fromFile(Resource resource) {

		PresentationTable ptable = new PresentationTable();
		XudmlMarshaller<PresentationTableT> unmarshaller = new XudmlMarshaller<PresentationTableT>();
		ptable.setXudmlObject(unmarshaller.unmarshall(resource));
		ptable.xudmlObject.getPresentationColumn().stream().forEach(column->{
			log.debug(column.getName());
			
		});
		
		
		log.debug(ptable.xudmlObject.getName());
		
		
		
		return ptable;
	}
	
	
}
