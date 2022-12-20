package com.te.lms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.te.lms.entity.Mentor;
import com.te.lms.repository.MentorRepository;
import com.te.lms.service.AdminService;

@SpringBootTest
class LearningManagementSystemApplicationTests {

	@MockBean
	private MentorRepository mentorRepository;
	@InjectMocks
	private AdminService adminService;

	@Test
	void test_getMentors() {
//		Mentor mentor=new Mentor("ABCD", "yugandhar262@gmail.com", null, null, null, null);
//		Mockito.when(mentorRepository.findAll()).thenReturn(mentor);
//		Mockito.when(mentorRepositorty.findAll()).thenReturn(mentor);
//		System.out.println(adminService.getMentors().get().size());
//		assertEquals(2,adminService.getMentors().get().size());
	}
}
