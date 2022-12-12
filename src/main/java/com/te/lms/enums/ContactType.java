package com.te.lms.enums;

public enum ContactType {
	OFFICIAL("OFFICIAL"), EMERGENCY("EMERGENCY");

	private final String contactType;

	private ContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactType() {
		return contactType;
	}

}
