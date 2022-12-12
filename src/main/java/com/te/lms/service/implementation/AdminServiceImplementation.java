package com.te.lms.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.te.lms.dto.ApproveDto;
import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.BatchUpdateDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;
import com.te.lms.dto.RejectDto;
import com.te.lms.dto.RequestListMessageDto;
import com.te.lms.dto.TechnologiesDto;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Batch;
import com.te.lms.entity.Employee;
import com.te.lms.entity.Mentor;
import com.te.lms.entity.Roles;
import com.te.lms.entity.Technologies;
import com.te.lms.enums.Status;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.BatchRepository;
import com.te.lms.repository.EmployeeRepository;
import com.te.lms.repository.MentorRepository;
import com.te.lms.repository.RequestRepository;
import com.te.lms.repository.RoleRepository;
import com.te.lms.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {

	private final MentorRepository mentorRepository;
	private final BatchRepository batchRepository;
	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;
	private final EmployeeRepository employeeRepository;
	private final RequestRepository requestRepository;

//RegisterMentor..............................................
	@Override
	public Optional<String> registerMentor(MentorDto mentorDto) {
		Mentor mentor = new Mentor();
		BeanUtils.copyProperties(mentorDto, mentor);

		List<Technologies> technologies = Lists.newArrayList();

		for (TechnologiesDto technologies2 : mentorDto.getSkills()) {
			Technologies technologies3 = new Technologies();
			BeanUtils.copyProperties(technologies2, technologies3);
			technologies.add(technologies3);
			technologies3.getMentor().add(mentor);
		}
		Optional<Roles> role = roleRepository.findByRoleName("ROLE_MENTOR");
		if (role.isPresent()) {
			AppUser appUser = AppUser.builder().username(mentor.getEmployeeId()).password("12345@Abc")
					.roles(Lists.newArrayList()).build();
			appUser.getRoles().add(role.get());
			Roles roles = Roles.builder().roleName("ROLE_MENTOR").appUsers(Lists.newArrayList()).build();
			mentor.setStatus(Status.ACTIVE);
			appUserRepository.save(appUser);
			mentor.setSkills(technologies);

			mentorRepository.save(mentor);
			return Optional
					.ofNullable("username = " + appUser.getUsername() + "\n" + "password = " + appUser.getPassword());
		}
		return null;

	}

//Update Mentor...............................................................
	@Override
	public Boolean updateMentor(String empId, MentorUpdateDto mentorUpdateDto) {
		Optional<Mentor> optionalMentor = mentorRepository.findById(empId);
		if (optionalMentor.isPresent()) {
			optionalMentor.get().setMentorName(mentorUpdateDto.getMentorName());
			optionalMentor.get().setMentorEmailId(mentorUpdateDto.getMentorEmailId());
			for (TechnologiesDto technologiesDto : mentorUpdateDto.getSkills()) {
				Technologies technologies = new Technologies();
				BeanUtils.copyProperties(technologiesDto, technologies);
				optionalMentor.get().getSkills().add(technologies);
			}
			mentorRepository.save(optionalMentor.get());
			return true;
		}
		return false;
	}

//Delete Mentor.................................................................
	@Override
	public Optional<Boolean> deleteMentor(String empId) {
		Optional<Mentor> optionalMentor = mentorRepository.findById(empId);
		if (optionalMentor.isPresent()) {
			optionalMentor.get().setStatus(Status.INACTIVE);
			mentorRepository.save(optionalMentor.get());
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

//REGISTER BATCH..............................................................................
	@Override
	public Optional<String> registerBatch(BatchRegisterDto batchRegisterDto) {
		Batch batch = new Batch();
		BeanUtils.copyProperties(batchRegisterDto, batch);

		List<Technologies> technologies = Lists.newArrayList();

		for (TechnologiesDto technologiesDto : batchRegisterDto.getTechnologies()) {
			Technologies technologies1 = new Technologies();
			BeanUtils.copyProperties(technologiesDto, technologies1);
			technologies.add(technologies1);
			technologies1.getBatch().add(batch);
		}
		batch.setStatus(Status.ACTIVE);
		batch.setTechnologies(technologies);

		Optional<Mentor> mentorData = mentorRepository.findByMentorName(batchRegisterDto.getMentorName());
		batch.setMentor(mentorData.get());
		batchRepository.save(batch);

		return Optional.ofNullable(batch.getBatchId());
	}

// Update Batch...................................................................................
	@Override
	public Optional<Boolean> updateBatch(BatchUpdateDto batchUpdateDto, String batchId) {
		Optional<Batch> optionalBatch = batchRepository.findById(batchId);
		if (optionalBatch.isPresent()) {
			/* setting the updated fields from the Dto */
			optionalBatch.get().setBatchName(batchUpdateDto.getBatchName());
			optionalBatch.get().setMentorName(batchUpdateDto.getMentorName());
			optionalBatch.get().setStartDate(batchUpdateDto.getStartDate());
			optionalBatch.get().setEndDate(batchUpdateDto.getEndDate());
			optionalBatch.get().setBatchStatus(batchUpdateDto.getBatchStatus());

			/* adding updated technologies to the list */
			for (TechnologiesDto technologiesDto : batchUpdateDto.getTechnologies()) {
				Technologies technologies = new Technologies();
				BeanUtils.copyProperties(technologiesDto, technologies);
				optionalBatch.get().getTechnologies().add(technologies);
			}
			batchRepository.save(optionalBatch.get());
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

//Delete Batch.............................................................................
	@Override
	public Optional<Boolean> deleteBatch(String batchId) {
		Optional<Batch> optionalBatch = batchRepository.findById(batchId);
		if (optionalBatch.isPresent()) {
			optionalBatch.get().setStatus(Status.INACTIVE);
			batchRepository.save(optionalBatch.get());
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(false);
	}

//Search Employee.......................................................................
	@Override
	public Optional<MentorDto> searchEmployee(String employeeId) {
		Optional<Mentor> optionalMentor = mentorRepository.findById(employeeId);
		MentorDto mentorDto = new MentorDto();
		if (optionalMentor.isPresent()) {
			BeanUtils.copyProperties(optionalMentor.get(), mentorDto);
			for (Technologies technologies : optionalMentor.get().getSkills()) {
				TechnologiesDto technologiesDto = new TechnologiesDto();
				BeanUtils.copyProperties(technologies, technologiesDto);
				mentorDto.getSkills().add(technologiesDto);
			}
			return Optional.ofNullable(mentorDto);
		}
		return null;
	}

//Approve........................................................................................
	@Override
	public Optional<RequestListMessageDto> approve(String employeeID, ApproveDto approveDto) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeID);
		if (optionalEmployee.isPresent()) {
			Optional<Batch> optionalBatch = batchRepository.findById(approveDto.getBatchId());
			if (optionalBatch.isPresent()) {
				optionalEmployee.get().setBatch(optionalBatch.get());
			}
			employeeRepository.save(optionalEmployee.get());
			Optional<Roles> role = roleRepository.findByRoleName("ROLE_EMPLOYEE");
			if (role.isPresent()) {
				AppUser appUser = AppUser.builder().username(employeeID).password("12345@Abc")
						.roles(Lists.newArrayList()).build();
				appUser.getRoles().add(role.get());
				Roles roles = Roles.builder().roleName("ROLE_EMPLOYEE").appUsers(Lists.newArrayList()).build();
				appUserRepository.save(appUser);
				RequestListMessageDto requestListMessageDto = new RequestListMessageDto();
				requestListMessageDto.setEmployeeEmail(optionalEmployee.get().getEmployeeEmail());
				requestListMessageDto.setMessage(
						"username = " + appUser.getUsername() + "\n" + "password = " + appUser.getPassword());
				requestRepository.deleteById(employeeID);
				return Optional.ofNullable(requestListMessageDto);
			}
		}
		return null;
	}

//Reject.........................................................................
	@Override
	public Optional<RequestListMessageDto> reject(String employeeId, RejectDto rejectDto) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
		if (optionalEmployee.isPresent()) {
			RequestListMessageDto requestListMessageDto = new RequestListMessageDto();
			requestListMessageDto.setEmployeeEmail(optionalEmployee.get().getEmployeeEmail());
			requestRepository.deleteById(employeeId);
			Optional<Employee> findById = employeeRepository.findById(employeeId);
			findById.get().setStatus(Status.INACTIVE);
			employeeRepository.save(optionalEmployee.get());
			return Optional.ofNullable(requestListMessageDto);
		}
		return Optional.ofNullable(null);
	}

//Batch List..................................................
	@Override
	public Optional<List<BatchRegisterDto>> batchList() {
		List<BatchRegisterDto> batchRegisterDtos = Lists.newArrayList();
		BatchRegisterDto batchRegisterDto = new BatchRegisterDto();
		List<Batch> findAll = batchRepository.findAll();
		for (Batch batch : findAll) {
			for (Technologies t : batch.getTechnologies()) {
				TechnologiesDto technologiesDto = new TechnologiesDto();
				BeanUtils.copyProperties(t, technologiesDto);
				batchRegisterDto.getTechnologies().add(technologiesDto);
			}
			
			BeanUtils.copyProperties(batch, batchRegisterDto);
			batchRegisterDtos.add(batchRegisterDto);
		}
		return Optional.ofNullable(batchRegisterDtos);
	}

//Mentor List.................................................................
	@Override
	public Optional<List<MentorDto>> mentorList() {
		List<MentorDto> mentorDtos = Lists.newArrayList();
		MentorDto mentorDto = new MentorDto();
		List<Mentor> findAll = mentorRepository.findAll();
		for (Mentor mentor : findAll) {
			for (Technologies t : mentor.getSkills()) {
				TechnologiesDto technologiesDto = new TechnologiesDto();
				BeanUtils.copyProperties(t, technologiesDto);
				mentorDto.getSkills().add(technologiesDto);
			}
			BeanUtils.copyProperties(mentor, mentorDto);
			mentorDtos.add(mentorDto);
		}
		return Optional.ofNullable(mentorDtos);
	}

}
