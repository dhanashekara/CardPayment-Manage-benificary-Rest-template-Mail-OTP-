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
import com.hcl.bankingapp.service.BankTransactionServiceImpl;
import com.hcl.bankingapp.service.UserDetailsServiceImpl;
import com.hcl.bankingapp.service.UserLoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BankTransactionControllerTest {

	@InjectMocks
	BankTransactionController bankTransactionController;

	@Mock
	BankTransactionServiceImpl bankTransactionServiceImpl;
	
	@Mock
	UserLoginServiceImpl UserLoginServiceImpl;
	
	@Mock
	UserDetailsServiceImpl userDetailsServiceImpl;
	

	MockMvc mockMvc;

	LoginDto loginDto;

	@Before
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.standaloneSetup(bankTransactionController).build();
		loginDto = new LoginDto();
		loginDto.setEmail("varun");
		loginDto.setPassword("varun@123");

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void transaction() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/transaction").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(loginDto))).andExpect(status().isOk());

	}

	@Test
	public void getAllPayeeDetails() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/getAll/1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(asJsonString(loginDto))).andExpect(status().isOk());

	}

//	@Test
//	public void saveCustomer() throws Exception {
//
//		mockMvc.perform(MockMvcRequestBuilders.post("/save").contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.ALL).content(asJsonString(loginDto))).andExpect(status().isOk());
//
//	}

}
