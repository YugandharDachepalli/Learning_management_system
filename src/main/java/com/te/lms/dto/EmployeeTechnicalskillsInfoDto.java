package com.te.lms.dto;

import com.te.lms.entity.Employee;

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
public class EmployeeTechnicalskillsInfoDto {
	private Integer skillsId;
	private String skillType;
	private String skillRating;
	private Integer yearOfExperience;
	private Employee employee;
}
