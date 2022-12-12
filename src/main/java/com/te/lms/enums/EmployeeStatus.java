package com.te.lms.enums;

public enum EmployeeStatus {
	ACTIVE("ACTIVE"),TERMINATED("TERMINATED"),ABSCONDED("ABSCONDED");
	
	private final String empoloyeeStatus;

	private EmployeeStatus(String empoloyeeStatus) {
		this.empoloyeeStatus = empoloyeeStatus;
	}

	public String getStatus() {
		return empoloyeeStatus;
	}

}
