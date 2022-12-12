package com.te.lms.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class RequestListDto {

	private Integer roleId;
	private String employeeId;
	private String employeeName;
	private LocalDate yearOfPassing;
	private Double percentage;
	private Integer yearsOfExperience;
	private String contactNo;
	
}
