package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lms.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String>{

}
