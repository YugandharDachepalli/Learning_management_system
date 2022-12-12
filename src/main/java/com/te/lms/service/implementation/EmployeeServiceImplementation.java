package com.te.lms.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.lms.dto.EmployeeAddressInfoDto;
import com.te.lms.dto.EmployeeContactInfoDto;
import com.te.lms.dto.EmployeeDto;
import com.te.lms.dto.EmployeeEducationInfoDto;
import com.te.lms.dto.EmployeeExperienceInfoDto;
import com.te.lms.dto.EmployeeTechnicalskillsInfoDto;
import com.te.lms.entity.Employee;
import com.te.lms.entity.EmployeeAddressInfo;
import com.te.lms.entity.EmployeeContactInfo;
import com.te.lms.entity.EmployeeEducationInfo;
import com.te.lms.entity.EmployeeExperienceInfo;
import com.te.lms.entity.EmployeeTechnicalskillsInfo;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService{

	private final EmployeeRepository employeeRepository;
	@Override
	public Optional<String> registerEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(employeeDto, employee);
				
		List<EmployeeAddressInfo> employeeAddressInfos= Lists.newArrayList();
		
		for(EmployeeAddressInfoDto addressInfoDto:employeeDto.getEmployeeAddressInfos()) {
			EmployeeAddressInfo addressInfo=new EmployeeAddressInfo();
			BeanUtils.copyProperties(addressInfoDto,addressInfo);
			employeeAddressInfos.add(addressInfo);
			addressInfo.setEmployee(employee);
		}
		employee.setEmployeeAddressInfos(employeeAddressInfos);
		
		List<EmployeeContactInfo> employeeContactInfos= Lists.newArrayList();
		for(EmployeeContactInfoDto contactInfoDto:employeeDto.getEmployeeContactInfos()) {
			EmployeeContactInfo employeeContactInfo = new EmployeeContactInfo();
			BeanUtils.copyProperties(contactInfoDto, employeeContactInfo);
			employeeContactInfos.add(employeeContactInfo);
			employeeContactInfo.setEmployee(employee);
		}
		employee.setEmployeeContactInfos(employeeContactInfos);
		
		List<EmployeeEducationInfo> employeeEducationInfos = Lists.newArrayList();
		for(EmployeeEducationInfoDto educationInfoDto:employeeDto.getEmployeeEducationInfos()) {
			EmployeeEducationInfo employeeEducationInfo = new EmployeeEducationInfo();
			BeanUtils.copyProperties(educationInfoDto, employeeEducationInfo);
			employeeEducationInfos.add(employeeEducationInfo);
			employeeEducationInfo.setEmployee(employee);
		}
		employee.setEmployeeEducationInfos(employeeEducationInfos);
		
		List<EmployeeExperienceInfo> employeeExperienceInfos = Lists.newArrayList();
		for(EmployeeExperienceInfoDto experienceInfoDto:employeeDto.getEmployeeExperienceInfos()) {
			EmployeeExperienceInfo employeeExperienceInfo = new EmployeeExperienceInfo();
			BeanUtils.copyProperties(experienceInfoDto, employeeExperienceInfo);
			employeeExperienceInfos.add(employeeExperienceInfo);
			employeeExperienceInfo.setEmployee(employee);
		}
		employee.setEmployeeExperienceInfos(employeeExperienceInfos);
		
		List<EmployeeTechnicalskillsInfo> employeeTechnicalskillsInfos = Lists.newArrayList();
		for(EmployeeTechnicalskillsInfoDto technicalskillsInfoDto:employeeDto.getEmployeeTechnicalskillsInfos()) {
			EmployeeTechnicalskillsInfo employeeTechnicalskillsInfo = new EmployeeTechnicalskillsInfo();
			BeanUtils.copyProperties(technicalskillsInfoDto,employeeTechnicalskillsInfo);
			employeeTechnicalskillsInfos.add(employeeTechnicalskillsInfo);
			employeeTechnicalskillsInfo.setEmployee(employee);
		}
		employee.setEmployeeTechnicalskillsInfos(employeeTechnicalskillsInfos);

		return Optional.ofNullable(employeeRepository.save(employee).getEmployeeName());
	}

}
