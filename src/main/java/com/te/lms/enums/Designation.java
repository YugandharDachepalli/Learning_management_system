package com.te.lms.enums;

public enum Designation {
	EMPLOYEE("EMPLOYEE"), MENTOR("MENTOR"), ADMIN("ADMIN");

	private final String designation;

	private Designation(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}
}
