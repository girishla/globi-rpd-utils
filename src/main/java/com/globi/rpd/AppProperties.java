package com.globi.rpd;

public enum AppProperties {
	INSTANCE;
	private String BASEPATH;

	public void setBasePath(String basePath) {
		this.BASEPATH = basePath;
	}

	public String getBasePath() {
		return this.BASEPATH;
	}

}