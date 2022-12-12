package com.te.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, String> {

	void save(Optional<Batch> optionalBatch);

	

}
