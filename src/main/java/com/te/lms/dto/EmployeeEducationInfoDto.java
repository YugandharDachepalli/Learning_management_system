package com.te.lms.dto;

import java.time.LocalDate;

import com.te.lms.entity.Employee;
import com.te.lms.enums.EducationType;

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

public class EmployeeEducationInfoDto {
	private Integer educationId;
	private EducationType educationType;
	private LocalDate yearOfPassing;
	private Double percentage;
	private String universityName;
	private String instituteName;
	private String specialization;
	private String state;
	private Employee employee;
}
