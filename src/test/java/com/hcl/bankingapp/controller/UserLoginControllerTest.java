package com.hcl.bankingapp.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bankingapp.dto.LoginDto;
import com.hcl.bankingapp.service.UserLoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginControllerTest {

	@InjectMocks
	UserLoginController userLoginController;

	@Mock
	UserLoginServiceImpl UserLoginServiceImpl;

	MockMvc mockMvc;

	LoginDto loginDto;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.standaloneSetup(userLoginController).build();
		loginDto=new LoginDto();
		loginDto.setEmail("varun");
		loginDto.setPassword("varun@123");

	}

	@Test
	public void testUserLogin() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/user/login/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(loginDto))).andExpect(status().isOk());

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
