package com.te.lms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.Lists;
import com.te.lms.entity.Admin;
import com.te.lms.entity.AppUser;
import com.te.lms.entity.Roles;
import com.te.lms.repository.AdminRepository;
import com.te.lms.repository.AppUserRepository;
import com.te.lms.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class LearningManagementSystemApplication {
	
	private final RoleRepository roleRepository;
	private final AdminRepository adminRepository;
	private final AppUserRepository appUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(LearningManagementSystemApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			
			Roles employee = Roles.builder().roleName("ROLE_EMPLOYEE").build();
			Roles mentor = Roles.builder().roleName("ROLE_MENTOR").build();
			Roles admin = Roles.builder().roleName("ROLE_ADMIN").appUsers(Lists.newArrayList()).build();

			Admin admin1 = Admin.builder().adminId("AD001").adminName("YUga").build();

			AppUser adminCredentials = AppUser.builder().username(admin1.getAdminId())
					.password("leo123").roles(Lists.newArrayList()).build();

			roleRepository.save(mentor);
			roleRepository.save(employee);

			adminRepository.save(admin1);

			adminCredentials.getRoles().add(admin);
			admin.getAppUsers().add(adminCredentials);

			roleRepository.save(admin);
			appUserRepository.save(adminCredentials);
		};
	}

}
