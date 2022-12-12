package com.te.lms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
@Entity
public class EmployeeSecondaryInfo {
	@Id
	private String panNo;
	private String aadhaarNo;
	private String motherName;
	private String fatherName;
	private String spouseName;
	private String passportNo;
	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;
	
	@OneToOne(mappedBy = "employeeSecondaryInfo",cascade = CascadeType.ALL)
	private Employee employee;
	

}
