package com.globi.rpd.cli;

public class StandardiseSubjectAreaNames implements RpdObjectCommand<Boolean, String> {

	@Override
	public Boolean execute(String subjectAreaName) {

		if(!subjectAreaName.toUpperCase().equals("ALL"))
			throw new IllegalArgumentException("Invalid Argument " + subjectAreaName );
		
		
		
		
		return true;
	}



}
