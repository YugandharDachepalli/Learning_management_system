package com.te.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.common.collect.Lists;

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
public class Technologies {
	@Id
	private String technology;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(joinColumns = @JoinColumn(name="technology_fk"),inverseJoinColumns = @JoinColumn(name="batch_fk"))
	private List<Batch> batch=Lists.newArrayList();
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "mentor_technologies",joinColumns = @JoinColumn(name = "technology_fk"),inverseJoinColumns = @JoinColumn(name = "mentor_fk"))
	private List<Mentor> mentor=Lists.newArrayList();

}
