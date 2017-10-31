package com.globi.rpd;

public interface RpdMarshalledObject {

	String getResourceUri();
	boolean isUnmarshalled();
	void marshall();
}
