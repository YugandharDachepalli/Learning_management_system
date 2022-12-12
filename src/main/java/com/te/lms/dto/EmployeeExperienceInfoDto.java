package com.te.lms.dto;

import java.time.LocalDate;

import com.te.lms.entity.Employee;
import com.te.lms.enums.Designation;

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

public class EmployeeExperienceInfoDto {

	private Integer experienceId;
	private String companyName;
	private Integer yearsOfExperience;
	private LocalDate dateOfJoining;
	private LocalDate dateOfReleiving;
	private String location;
	private String designation;

	private Employee employee;

}
