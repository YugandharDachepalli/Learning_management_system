package com.te.lms.dto;

import java.util.List;

import com.te.lms.entity.Batch;
import com.te.lms.entity.Technologies;

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
public class MentorUpdateDto {
	private String mentorName;
	private String mentorEmailId;
	private List<TechnologiesDto> skills;
	private String employeeId;
	private Batch batch;
}
