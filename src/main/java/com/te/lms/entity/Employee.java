package com.te.lms.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.collect.Lists;
import com.te.lms.enums.Designation;
import com.te.lms.enums.EmployeeStatus;
import com.te.lms.enums.Gender;
import com.te.lms.enums.Status;

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
@Table(name = "employee_primary_Info")
public class Employee {
	@Id
	private String employeeId;
	private String employeeName;
	private LocalDate employeeDoj;
	private LocalDate employeeDob;
	private String employeeEmail;
	private String bloodGroup;
	private String employeeNationality;
	
	@Enumerated(EnumType.STRING)
	private Designation designation;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	private EmployeeStatus employeeStatus;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "secondary_info_fk")
	private EmployeeSecondaryInfo employeeSecondaryInfo;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeEducationInfo> employeeEducationInfos = Lists.newArrayList();

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeContactInfo> employeeContactInfos;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeAddressInfo> employeeAddressInfos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bank_details_fk")
	private EmployeeBankDetails employeeBankDetails;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeExperienceInfo> employeeExperienceInfos;

	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<EmployeeTechnicalskillsInfo> employeeTechnicalskillsInfos;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Batch batch;
	
}
