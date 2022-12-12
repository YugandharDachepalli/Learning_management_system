package com.te.lms.service;
import java.util.List;
import java.util.Optional;

import com.te.lms.dto.ApproveDto;
import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.BatchUpdateDto;
import com.te.lms.dto.MentorDeleteDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;
import com.te.lms.dto.RejectDto;
import com.te.lms.dto.RequestListMessageDto;

public interface AdminService {

	Optional<String> registerMentor(MentorDto mentorDto);
	
	Boolean updateMentor(String empId, MentorUpdateDto mentorUpdateDto);

	Optional<Boolean> deleteMentor(String empId);

	Optional<Boolean> deleteBatch(String batchId);

	Optional<Boolean> updateBatch(BatchUpdateDto batchUpdateDto, String batchId);

	Optional<MentorDto> searchEmployee(String employeeId);

	Optional<List<BatchRegisterDto>> batchList();

	Optional<RequestListMessageDto> approve(String employeeID, ApproveDto approveDto);

	Optional<RequestListMessageDto> reject(String employeeId, RejectDto rejectDto);

	Optional<List<MentorDto>> mentorList();

	Optional<String> registerBatch(BatchRegisterDto batchRegisterDto);

}
