package com.te.lms.enums;

public enum BatchStatus {
	TOBESTARTED("TOBESTARTED"),INPROGRESS("INPROGRESS"),COMPLETED("COMPLETED");
	
	private final String status;

	private BatchStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
