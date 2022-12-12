package com.te.lms.enums;

public enum MaritalStatus {
	UNMARRIED("UNMARRIED"),MARRIED("MARRIED"),DIVORCED("DIVORCED");
	
	private final String maritalStatus;

	private MaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}
	
}
