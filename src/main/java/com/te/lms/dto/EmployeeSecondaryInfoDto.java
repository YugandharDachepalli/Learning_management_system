package com.te.lms.dto;

import com.te.lms.entity.Employee;
import com.te.lms.enums.MaritalStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeSecondaryInfoDto {
	private String panNo;
	private String aadhaarNo;
	private String motherName;
	private String fatherName;
	private String spouseName;
	private String passportNo;
	private MaritalStatus maritalStatus;
	private Employee employee;

}
