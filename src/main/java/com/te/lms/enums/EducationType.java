package com.te.lms.enums;

public enum EducationType {
	GRADUATE("GRADUATE"),POSTGRADUATE("POSTGRADUATE"),INTERMEDDIATE("INTERMEDDIATE");
	
	private final String educationType;

	private EducationType(String educationType) {
		this.educationType = educationType;
	}

	public String getEducationType() {
		return educationType;
	}
	

}
