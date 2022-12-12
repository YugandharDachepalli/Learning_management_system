package com.te.lms.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.te.lms.dto.EmployeeDto;
import com.te.lms.dto.EmployeeEducationInfoDto;
import com.te.lms.entity.RequestList;
import com.te.lms.repository.RequestRepository;
import com.te.lms.service.RequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestServiceImplementation implements RequestService{
	
	private final RequestRepository requestRepository;
	
	@Override
	public void addToRequest(EmployeeDto employeeDto) {
		RequestList requestList = new RequestList();
		BeanUtils.copyProperties(employeeDto, requestList);
		for(EmployeeEducationInfoDto educationInfo:employeeDto.getEmployeeEducationInfos()) {
			requestList.setPercentage(educationInfo.getPercentage());
			requestList.setYearOfPassing(educationInfo.getYearOfPassing());
		}
		requestList.setContactNo(employeeDto.getEmployeeContactInfos().get(0).getContactNo());
		requestList.setYearsOfExperience(employeeDto.getEmployeeExperienceInfos().get(0).getYearsOfExperience());
		requestRepository.save(requestList);
	}

}
