package com.te.lms.enums;

public enum AddressType {
	
	TEMPORARY("TEMPORARY"),PERMANENT("PERMANENT");
	private final String addressType;

	private AddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getAddressType() {
		return addressType;
	}

}
