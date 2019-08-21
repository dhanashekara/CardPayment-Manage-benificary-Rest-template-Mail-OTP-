package com.hcl.bankingapp.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.bankingapp.dto.AccountDetailsDTO;
import com.hcl.bankingapp.dto.CreditCardTransactionDTO;
import com.hcl.bankingapp.service.CreditCardService;

import junit.framework.TestCase;
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = { TestCase.class, CreditCardController.class })
@WebAppConfiguration
public class CreditCardControllerTest {
	
	@InjectMocks
	private CreditCardController creditController;
	@Mock
	private CreditCardService creditService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(creditController).build();
	}

	@Test
	public void getAccountDetailsTest() throws JsonProcessingException, Exception {
		AccountDetailsDTO dto=new AccountDetailsDTO();
		dto.setAccountbalance(34567.00);
		dto.setAccountMail("pradeep.aj28@gmail.com");
		dto.setAccountNo(234567L);
		dto.setCreditBalance(345678.00);
		dto.setUserName("pradeep");
		ResponseEntity<AccountDetailsDTO> expResult = new ResponseEntity<>(dto, HttpStatus.OK);
		when(creditService.getAccountDetails(Mockito.anyLong())).thenReturn(dto);
		mockMvc.perform(get("bank/user/{userId}", "Seller").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(dto))).andReturn();
		ResponseEntity<AccountDetailsDTO> actResult = creditController.getAccountDetails(4567L);
		assertEquals(expResult, actResult);
		
	}
	
	
	@Test
	public void getStatementTest() throws JsonProcessingException, Exception {
		CreditCardTransactionDTO cdto=new CreditCardTransactionDTO();
		cdto.setAccountNumber(23456789L);
		cdto.setDebitAmount(345678.00);
		cdto.setDescription("succes");
		cdto.setPaymentType("credit");
		cdto.setReceiverName("pradi");
		List<CreditCardTransactionDTO> prolist = new ArrayList<>();
		
		ResponseEntity<List<CreditCardTransactionDTO>> expResult = new ResponseEntity<>(prolist, HttpStatus.OK);
		
		when(creditService.getStatement(Mockito.anyLong())).thenReturn(prolist);
		mockMvc.perform(get("bank/user/{userId}/statement/}", "1").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(prolist))).andReturn();
		ResponseEntity<List<CreditCardTransactionDTO>> actResult = creditController.getStatement(1L);
		assertEquals(expResult, actResult);
	}
	
	public static String asJsonString(final Object object) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);

	}
	
}