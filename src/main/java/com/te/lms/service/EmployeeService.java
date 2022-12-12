package com.te.lms.service;

import java.util.Optional;

import com.te.lms.dto.EmployeeDto;

public interface EmployeeService {

	Optional<String> registerEmployee(EmployeeDto employeeDto);

}
