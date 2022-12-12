package com.te.lms.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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

public class EmployeeExperienceInfo {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer experienceId;
	private String companyName;
	private Integer yearsOfExperience;
	private LocalDate dateOfJoining;
	private LocalDate dateOfReleiving;
	private String location;
	
	
	private String designation;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_fk")
	private Employee employee;

}
