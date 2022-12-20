package com.te.lms.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;
import com.te.lms.dto.ApproveDto;
import com.te.lms.dto.BatchRegisterDto;
import com.te.lms.dto.BatchUpdateDto;
import com.te.lms.dto.MentorDto;
import com.te.lms.dto.MentorUpdateDto;
import com.te.lms.dto.RejectDto;
import com.te.lms.dto.RequestListMessageDto;
import com.te.lms.dto.TechnologiesDto;
import com.te.lms.entity.Batch;
import com.te.lms.enums.BatchStatus;
import com.te.lms.enums.Status;
import com.te.lms.exception.EmployeeNotFoundException;
import com.te.lms.response.ApiResponse;
import com.te.lms.service.AdminService;

	 @AutoConfigureMockMvc
	 @SpringBootTest
	 class AdminControllerTest {

	 	@Autowired
	 	private MockMvc mockMvc;
	 	
	 	/*
	 	 * @Autowired 
	 	 * private WebApplicationContext context;
	 	 * 
	 	 */

	 	@MockBean
	 	private AdminService adminService;

	 	@InjectMocks
	 	private AdminController adminController;

	 	ObjectMapper objectMapper = new ObjectMapper();

		private Optional<String> mentorDto;
	 	
	 	@Before public void setUp() {
	 		
	 	   mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	 	  	// below code is giving Null Pointer exception.
	 		/* mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); */
	 	  
	 	   }
	 	 

//Test Case For Mentor Register.......................................................................... 
	   @Test
	   
	 	public void testmentorRegister() throws JsonProcessingException, Exception {
		   
	 		  MentorDto mentordto = MentorDto.builder().employeeId("TY001").mentorEmailId("yugandhar262@gmail.com")
	 				.mentorName("Yuga").status(Status.ACTIVE).skills(Lists.newArrayList()).build();
	 		  
	 	    Mockito.when(adminService.registerMentor(Mockito.any())).thenReturn(Optional.ofNullable("TY001"));
	 		String contentAsString = mockMvc
	 				.perform(MockMvcRequestBuilders.post("/admin/register/mentor")		
	 				.contentType(MediaType.APPLICATION_JSON_VALUE)
	 				.content(objectMapper.writeValueAsString(mentordto)))
	 				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
	 		
	 		ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
	 		assertEquals(apiResponse.getMessage(), "registration successful");
	 	}
	   
// test case for register mentor which returns bad Http response as bad Request
			@Test
			public void testRegisterMentor_Returns400() throws JsonProcessingException, Exception
					  {
				MentorDto mentorDto = MentorDto.builder().mentorEmailId("yugandhar262@gmail.com").mentorName("Yugandhar").employeeId("TY001")
						.skills(Lists.newArrayList()).build();
				Mockito.when(adminService.registerMentor(Mockito.any())).thenReturn(Optional.ofNullable(null));

				mockMvc.perform(MockMvcRequestBuilders.post("/admin/register/mentor").accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(mentorDto)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	 	 
	   
//Test Case For Mentor Update...............................................................	
		@Test
		
		public void testMentorUpdate() throws Exception{
			MentorUpdateDto  mentorUpdateDto = MentorUpdateDto.builder()
					.employeeId("Ty009").mentorEmailId("yugandhar262@gmail.com")
					.mentorName("Leo9").skills(Lists.newArrayList())
					.skills(Lists.newArrayList())
					.batch(new Batch()).build();
			 Mockito.when(adminService.updateMentor(Mockito.any(),Mockito.any())).thenReturn(true);
			 String contentAsString = mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/mentor/Ty001")
			 		.accept(MediaType.APPLICATION_JSON)
			 		.contentType(MediaType.APPLICATION_JSON)
			 		.content(objectMapper.writeValueAsString(mentorUpdateDto)))
			 		.andExpect(MockMvcResultMatchers.status().isOk())
			 		.andReturn()
			 		.getResponse().getContentAsString();
				ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
		 		assertEquals(apiResponse.getMessage(),"Mentor was successfully updated");
		}
		
		@Test
		public void testUpdateMentor_Returns400() throws Exception {

			 // MentorUpdateDto  mentorUpdateDto = new MentorUpdateDto();
			MentorUpdateDto  mentorUpdateDto = MentorUpdateDto.builder()
					.employeeId("Ty009").mentorEmailId("yugandhar262@gmail.com")
					.mentorName("Leo9").skills(Lists.newArrayList())
					.skills(Lists.newArrayList())
					.batch(new Batch()).build();

			Mockito.when(adminService.updateMentor(Mockito.any(), Mockito.any())).thenReturn(false);

			mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/mentor/Ty001")
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.content(objectMapper.writeValueAsString(mentorUpdateDto))
					.contentType(MediaType.APPLICATION_JSON))
			        .andExpect(MockMvcResultMatchers.status().isBadRequest());
		}

		
	
//Test Case For Delete Mentor.....................................................................................
				@Test
				
				public void testdeleteMentor() throws UnsupportedEncodingException, Exception {
					Mockito.when(adminService.deleteMentor(Mockito.anyString())).thenReturn(Optional.ofNullable(true));
					String contentAsString = mockMvc
							 .perform(
									MockMvcRequestBuilders.put("/admin/delete/mentor/Ty001").contentType(MediaType.APPLICATION_JSON_VALUE))
							.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
					ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
					assertEquals(apiResponse.getMessage(), "record was successfully deleted");

				}
				
	// test case to delete mentor that return Error 400
				@Test
				public void testDeleteMentor_Returns400() throws Exception {
					MentorDto mentorDto = MentorDto.builder().employeeId("Ty001").mentorEmailId("yugandhra262@gmail.com").build();
					Mockito.when(adminService.deleteMentor(mentorDto.getEmployeeId())).thenReturn(Optional.ofNullable(false));

					mockMvc.perform(MockMvcRequestBuilders.put("/admin/delete/mentor/Ty001")
							.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());

				}


	
				
//Test Case For Batch Register.........................................................................
	 	 @Test
	 	 
		 	public void testBatchResgister() throws JsonProcessingException, Exception {
	 		objectMapper.registerModule(new JavaTimeModule());
	 		BatchRegisterDto batchRegisterDto=BatchRegisterDto.builder().batchId("Tyoo1").batchName("Sep12").mentorName("Leo")
	 			 .batchStatus(BatchStatus.INPROGRESS).startDate(LocalDate.of(2021, 01, 02)).endDate(LocalDate.of(2022, 01, 02))
                 .technologies(Lists.newArrayList()).build();
		 		  
		 	    Mockito.when(adminService.registerBatch(Mockito.any())).thenReturn(Optional.ofNullable("Tyoo1"));
		 		String contentAsString = mockMvc
		 				                .perform(MockMvcRequestBuilders.post("/admin/register/batch")
		 						        .contentType(MediaType.APPLICATION_JSON_VALUE)
		 						        .content(objectMapper.writeValueAsString(batchRegisterDto)))
		 				                .andExpect(MockMvcResultMatchers.status().isOk())
		 				                .andReturn()
		 				                .getResponse()
		 				                .getContentAsString();
		 		
		 		ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
		 		assertEquals(apiResponse.getMessage(),"registration successful");
	
	 	 }
	 	 
// test case for Batch Register which returns bad Http response as bad Request
	 				@Test
	 				public void testRegisterBatch_Returns400() throws JsonProcessingException, Exception
	 						  {
	 					BatchRegisterDto batchRegisterDto=BatchRegisterDto.builder().batchId("Tyoo1").batchName("Sep12").mentorName("Leo")
	 				 			 .batchStatus(BatchStatus.INPROGRESS).startDate(null).endDate(null)
	 			                 .technologies(Lists.newArrayList()).build();
	 					
	 					Mockito.when(adminService.registerBatch(Mockito.any())).thenReturn(Optional.ofNullable(null));

	 					mockMvc.perform(MockMvcRequestBuilders.post("/admin/register/batch").accept(MediaType.APPLICATION_JSON_VALUE)
	 							.contentType(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(batchRegisterDto)))
	 					.andExpect(MockMvcResultMatchers.status().isBadRequest());
	 		}
	 	 
//Test Case For Batch Update...............................................................	
			@Test
			public void testBatchUpdate() throws Exception{
				
				BatchUpdateDto  batchUpdateDto = BatchUpdateDto.builder()
						.batchName("Sep13").batchStatus(BatchStatus.TOBESTARTED)
						.startDate(null)
						.endDate(null)
						.mentorName("Liza")
						.technologies(Lists.newArrayList())
						.build();
				 Mockito.when(adminService.updateBatch(Mockito.any(),Mockito.any())).thenReturn(Optional.ofNullable(true));
				String contentAsString= mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/batch/Tyoo1")
				 		.accept(MediaType.APPLICATION_JSON)
				 		.contentType(MediaType.APPLICATION_JSON)
				 		.content(objectMapper.writeValueAsString(batchUpdateDto)))
				 		.andExpect(MockMvcResultMatchers.status().isOk())
				 		.andReturn()
				 		.getResponse().getContentAsString();
				ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
		 		assertEquals(apiResponse.getMessage(),"Batch was successfully updated");
				
			}

// test case for Update batch that return response as BAD REQUEST
			@Test
			public void testUpdateBatch_Returns400() throws Exception {

				BatchUpdateDto  batchUpdateDto = BatchUpdateDto.builder()
						.batchName("Sep13").batchStatus(BatchStatus.TOBESTARTED)
						.startDate(null)
						.endDate(null)
						.mentorName("Liza")
						.technologies(Lists.newArrayList())
						.build();

				Mockito.when(adminService.updateBatch(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(false));

				mockMvc.perform(MockMvcRequestBuilders.put("/admin/update/batch/Tyoo1")
						.accept(MediaType.APPLICATION_JSON_VALUE)
						.content(objectMapper.writeValueAsString(batchUpdateDto))
						.contentType(MediaType.APPLICATION_JSON))
				        .andExpect(MockMvcResultMatchers.status().isBadRequest());
			}
			
//Test Case For Delete Batch.....................................................................................
			@Test
			public void testdeleteBatch() throws UnsupportedEncodingException, Exception {
				Mockito.when(adminService.deleteBatch(Mockito.anyString())).thenReturn(Optional.ofNullable(true));
				String contentAsString = mockMvc
						 .perform(
								MockMvcRequestBuilders.put("/admin/delete/batch/Tyoo1").contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
				ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
				assertEquals(apiResponse.getMessage(), "record was successfully deleted");

			}
			
// test case for delete batch that return response as BAD REQUEST
			@Test
			public void testDeleteBatch_Returns400() throws Exception {
				String batchId = "Batch-01";

				Mockito.when(adminService.deleteBatch(batchId)).thenReturn(Optional.ofNullable(false));

				mockMvc.perform(MockMvcRequestBuilders.put("/admin/delete/batch/Batch-01")
						.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());

			}

			
			
//Test Case For Search Employee.....................................................................................
			@Test
			
			public void testsearchEmployee() throws UnsupportedEncodingException, Exception {
				
				 MentorDto mentordto = MentorDto.builder().employeeId("Ty001").mentorEmailId("yugandhar262@gmail.com")
			 				.mentorName("Yuga").skills(Lists.newArrayList()).build();
				 
				 List<TechnologiesDto> skills = Lists.newArrayList();
				 TechnologiesDto technologiesDto=new TechnologiesDto();
				 technologiesDto.setTechnology("Java");
				 
				Mockito.when(adminService.searchEmployee(Mockito.any())).thenReturn(Optional.ofNullable(mentordto));
				
				String contentAsString = mockMvc
						 .perform(MockMvcRequestBuilders.get("/admin/search/Ty001").contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
				ApiResponse<MentorDto> apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
				assertEquals(apiResponse.getMessage(), "Mentor is found for the employee id "+mentordto.getEmployeeId());
				
			}
 
			@Test
			public void testSearchEmployee_Returns400() throws Exception {
				MentorDto mentorDto = new MentorDto();

				Mockito.when(adminService.searchEmployee(Mockito.any())).thenReturn(Optional.ofNullable(null));

				mockMvc.perform(MockMvcRequestBuilders.get("/admin/search/Ty001")
						.accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isBadRequest());
			}

			
//Test Case For ApproveRequest....................................................................................................
			
			@Test
			public void testApproveRequest() throws JsonProcessingException, UnsupportedEncodingException, Exception {
				ApproveDto approveDto=ApproveDto.builder().batchId("Ty001").batchName("Sep12").build();
				
				RequestListMessageDto messageDto=RequestListMessageDto.builder().employeeEmail("yugandhar262@gmail.com").message("Approved").build();
				
				Mockito.when(adminService.approve(Mockito.any(),Mockito.any())).thenReturn(Optional.ofNullable(messageDto));
				
				String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/admin/approve/Ty001")
						                 .accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(approveDto))
						                 .contentType(MediaType.APPLICATION_JSON_VALUE))
						                 .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
				ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);
				assertEquals(apiResponse.getMessage(),"Employee is approved" );
				}
			
			
			@Test
			public void testApproveRequest_Returns400() throws JsonProcessingException, Exception {
				ApproveDto approveDto = ApproveDto.builder().batchId("TY001").batchName("SEP-21").build();

				Mockito.when(adminService.approve(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(null));

				mockMvc.perform(MockMvcRequestBuilders.post("/admin/approve/Ty001")
						.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(approveDto))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(MockMvcResultMatchers.status().isBadRequest());

			}
			
			
//Test Case For RejectRequest......................................................................................................			
			@Test
			public void testRejectRequest() throws JsonProcessingException, UnsupportedEncodingException, Exception ,AssertionError{
				RejectDto rejectDto = RejectDto.builder().reason("Need to imporve").build();
				
			RequestListMessageDto messageDto=RequestListMessageDto.builder().employeeEmail("yugandhar262@gmail.com").message("Rejected").build();
				
				Mockito.when(adminService.reject(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(messageDto));

				String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/admin/reject/TY001")
								        .accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(rejectDto))
								        .contentType(MediaType.APPLICATION_JSON_VALUE))
						                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
				
				ApiResponse apiResponse = objectMapper.readValue(contentAsString, ApiResponse.class);

				assertEquals(apiResponse.getMessage(),"Sorry but your request can not be approved" );
				}
			
			@Test
			public void testRejectRequest_Returns400() throws JsonProcessingException, UnsupportedEncodingException, Exception {
				RejectDto rejectDto = RejectDto.builder().reason("Need to imporve").build();
				
				RequestListMessageDto messageDto=RequestListMessageDto.builder().employeeEmail("yugandhar262@gmail.com").message("Rejected").build();
				
				Mockito.when(adminService.reject(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(null));

				String contentAsSting = mockMvc
						.perform(MockMvcRequestBuilders.post("/admin/reject/TY001")
								.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(rejectDto))
								.contentType(MediaType.APPLICATION_JSON_VALUE))
						       .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse()
						       .getContentAsString();
				EmployeeNotFoundException exception = objectMapper.readValue(contentAsSting, EmployeeNotFoundException.class);
				assertEquals("unable to find the employee", exception.getMessage());

			}
	
			
			
			
			@Test
			public void testBatchList() throws Exception {
				List<BatchRegisterDto> batchRegisterDtos=Lists.newArrayList();
				BatchRegisterDto batchRegisterDto=new BatchRegisterDto();
				BatchRegisterDto batchRegisterDto1=new BatchRegisterDto();
				batchRegisterDtos.add(batchRegisterDto1);
				batchRegisterDtos.add(batchRegisterDto);
				
				Mockito.when(adminService.batchList()).thenReturn(Optional.ofNullable(batchRegisterDtos));
				mockMvc.perform(MockMvcRequestBuilders.get("/admin/list/batch").accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk())
						.andDo(MockMvcResultHandlers.print());
			}
			@Test
			public void testMentorList() throws Exception {
				List<MentorDto> mentorDtos=Lists.newArrayList();
				MentorDto mentorDto1=new MentorDto();
				MentorDto mentorDto2=new MentorDto();
				mentorDtos.add(mentorDto2);
				mentorDtos.add(mentorDto1);
				
				Mockito.when(adminService.mentorList()).thenReturn(Optional.ofNullable(mentorDtos));
				mockMvc.perform(MockMvcRequestBuilders.get("/admin/list/mentor").accept(MediaType.APPLICATION_JSON_VALUE)
						.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(MockMvcResultMatchers.status().isOk())
						.andDo(MockMvcResultHandlers.print());
			}
	 }
			


				



	

		






