package com.te.lms.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.EmployeeDto;
import com.te.lms.response.ApiResponse;
import com.te.lms.service.EmployeeService;
import com.te.lms.service.RequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/public")
public class ApplicationController {
	private final EmployeeService employeeService;
	private final RequestService requestService;
	
	@PostMapping(path = "/employee/register")
	public ApiResponse<String> registerEmployee(@RequestBody EmployeeDto employeeDto){
		Optional<String> empId = employeeService.registerEmployee(employeeDto);
		if(empId.isPresent())
		{
			requestService.addToRequest(employeeDto);
			return new ApiResponse<String>("Your request will be Approved in some time,Please Wait!","emp name who got registered is "+ empId.get());
		}
		return new ApiResponse<String>("employee registration unsuccessfull",null);
		
	}
	
}
