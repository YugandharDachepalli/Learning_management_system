package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

}
