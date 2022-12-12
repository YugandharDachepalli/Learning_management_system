package com.te.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.checkerframework.common.aliasing.qual.Unique;

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
public class Mentor {

	private String mentorName;
	private String mentorEmailId;
	
	@ManyToMany(mappedBy = "mentor",cascade = CascadeType.ALL)
	private List<Technologies> skills;
	
	@Id
	private String employeeId;
	
	@OneToOne(mappedBy = "mentor",cascade = CascadeType.ALL)
	private Batch batch;
	
	@Enumerated(EnumType.STRING)
	private Status status;
}
