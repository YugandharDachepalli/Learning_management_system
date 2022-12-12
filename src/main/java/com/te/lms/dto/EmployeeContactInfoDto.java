package com.te.lms.dto;

import com.te.lms.entity.Employee;
import com.te.lms.enums.ContactType;

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
public class EmployeeContactInfoDto {
	private ContactType contactType; 
	private String contactNo;
	private Employee employee;
}
