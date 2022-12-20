package com.te.lms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.te.lms.dto.ApproveDto;
import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.BatchUpdateDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;
import com.te.lms.dto.RejectDto;
import com.te.lms.dto.RequestListMessageDto;
import com.te.lms.email.SendEmail;
import com.te.lms.exception.BatchDeletionFailedException;
import com.te.lms.exception.BatchUpdationFailedException;
import com.te.lms.exception.EmployeeCannotBeApprovedException;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.exception.MentorDeletionFailedException;
import com.te.lms.exception.MentorUpdationFailedException;
import com.te.lms.exception.NoMentorsFoundException;
import com.te.lms.exception.RegistrationFailedException;
import com.te.lms.response.ApiResponse;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

	private final AdminService adminService;
	private final SendEmail sendEmail;
//
////Mentor Register...................................................
//	@PostMapping(path = "/register/mentor")
//	public ApiResponse<String> registerMentor(@RequestBody MentorDto mentorDto) {
//		Optional<String> mentor = adminService.registerMentor(mentorDto);
//		if(mentor.isPresent())
//		sendEmail.sendEmail("yugandhar262@gmail.com", mentorDto.getMentorEmailId(), "Welcome to the team",
//				"You are now registered as a mentor in the team of techno elevate. \n" + mentor.get());
//		return new ApiResponse<String>("registration successful", "You will receive credentials in your email");
//	}
	
	//Mentor Register...................................................
		@PostMapping(path = "/register/mentor")
		public ApiResponse<String> registerMentor(@RequestBody MentorDto mentorDto) {
			Optional<String> mentor = adminService.registerMentor(mentorDto);
			if(mentor.isPresent()) {
			sendEmail.sendEmail("yugandhar262@gmail.com", mentorDto.getMentorEmailId(), "Welcome to the team",
					"You are now registered as a mentor in the team of techno elevate. \n" + mentor.get());
			return new ApiResponse<String>("registration successful", "You will receive credentials in your email");
			}
			throw new RegistrationFailedException("Unable to register the mentor please check the details and try again");
		}

//Mentor Update........................................................................
	@PutMapping(path = "/update/mentor/{empId}")
	public ApiResponse<String> updateMentor(@PathVariable(name = "empId") String empId,
			@RequestBody MentorUpdateDto mentorUpdateDto) {
		Boolean isUpdated = adminService.updateMentor(empId, mentorUpdateDto);
		if (isUpdated) {
			return new ApiResponse<String>("Mentor was successfully updated",
					"Mentor with mentor id  '" + empId + "' got updated.");
		}
		throw new MentorUpdationFailedException("Unable to Update the Mentor please check the details and try again");
	
	}

//Mentor Delete ..........................................................................
	@PutMapping(path = "/delete/mentor/{empId}")
	public ApiResponse<String> deleteMentor(@PathVariable(name = "empId") String empId) {
		Optional<Boolean> optionalMentor = adminService.deleteMentor(empId);
		if (optionalMentor.get() == true) {
			return new ApiResponse<String>("record was successfully deleted",
					"mentor with employee id  '" + empId + "' got deleted.");
		}
		throw new MentorDeletionFailedException("Unable to Delete the Mentor please check the details and try again");
	}

//Batch Register.....................................................................................
	@PostMapping(path = "/register/batch")
	public ApiResponse<String> registerBatch(@RequestBody BatchRegisterDto batchRegisterDto) {
		Optional<String> batch = adminService.registerBatch(batchRegisterDto);
		if(batch.isPresent()) {
		return new ApiResponse<String>("registration successful", "Batch aadded in the database." + batchRegisterDto);
		}
		throw new RegistrationFailedException("Unable to register the Batch please check the details and try again");
	}

//Batch Update...........................................................................
	@PutMapping(path = "/update/batch/{batchId}")
	public ApiResponse<String> updateBatch(@PathVariable(name = "batchId") String batchId,
			@RequestBody BatchUpdateDto batchUpdateDto) {
		Optional<Boolean> isUpdated = adminService.updateBatch(batchUpdateDto, batchId);
		if (isUpdated.get()) {
			return new ApiResponse<String>("Batch was successfully updated",
					"Batch with batch id  '" + batchId + "' got updated.");
		}
		throw new BatchUpdationFailedException("Unable to Delete the Batch please check the details and try again");
	}

//Delete Batch..............................................................................
	@PutMapping(path = "/delete/batch/{batchId}")
	public ApiResponse<String> deleteBatch(@PathVariable(name = "batchId") String batchId) {
		Optional<Boolean> optionalMentor = adminService.deleteBatch(batchId);
		if (optionalMentor.get() == true) {
			return new ApiResponse<String>("record was successfully deleted",
					"batch with batch id  '" + batchId + "' got deleted.");
		}
		throw new BatchDeletionFailedException("Unable to Delete the Batch please check the details and try again");
	}
	

//Search Mentor.........................................................................................

	@GetMapping(path = "/search/{employeeId}")
	public ApiResponse<MentorDto> searchEmployee(@PathVariable(name = "employeeId") String employeeId) {
		Optional<MentorDto> mentorDto = adminService.searchEmployee(employeeId);
		if (mentorDto.isPresent()) {
			return new ApiResponse<MentorDto>("Mentor is found for the employee id " + employeeId, mentorDto.get());
		}
		throw new NoMentorsFoundException("no mentors found");
	}

//Approve.......................................................
	@PostMapping(path = "/approve/{employeeId}")
	public ApiResponse<String> approve(@PathVariable(name = "employeeId") String employeeID,
			@RequestBody ApproveDto approveDto) {
		Optional<RequestListMessageDto> isPresent = adminService.approve(employeeID, approveDto);
		if (isPresent.isPresent()) {
			sendEmail.sendEmail("yugandhar262@gmail.com", isPresent.get().getEmployeeEmail(), "Welcome to the Company",
					"You are now registered as a employee in the team of techno elevate. \n"
							+ isPresent.get().getMessage());

			return new ApiResponse<String>("Employee is approved",
					"You will receive credentials in " + isPresent.get().getEmployeeEmail());
		}
	
		throw new EmployeeCannotBeApprovedException("employee cannot be approved");
	}
	



//Reject..................................................................................
	@PostMapping(path = "/reject/{employeeId}")
	public ApiResponse<String> reject(@PathVariable(name = "employeeId") String employeeId,
			@RequestBody RejectDto rejectDto) {
		Optional<RequestListMessageDto> isPresent = adminService.reject(employeeId, rejectDto);

		if (isPresent.isPresent()) {
			sendEmail.sendEmail("yugandhar262@gmail.com", isPresent.get().getEmployeeEmail(), "Rejected",
					"You are rejected because: \n" + rejectDto.getReason());
			return new ApiResponse<String>("Sorry but your request can not be approved",
					"you will get details in your email");
		}
		throw new EmployeeNotFoundException("unable to find the employee");
	}

//Batch List............................................................................
	@GetMapping(path = "/list/batch")
	public ResponseEntity<List<BatchRegisterDto>> batchList() {
		Optional<List<BatchRegisterDto>> optionalList = adminService.batchList();
		return ResponseEntity.ok(optionalList.get());
	}

//Mentor List.............................................................................
	@GetMapping(path = "/list/mentor")
	public ResponseEntity<List<MentorDto>> mentorList() {
		Optional<List<MentorDto>> optionalList = adminService.mentorList();
		return ResponseEntity.ok(optionalList.get());
	}

}
