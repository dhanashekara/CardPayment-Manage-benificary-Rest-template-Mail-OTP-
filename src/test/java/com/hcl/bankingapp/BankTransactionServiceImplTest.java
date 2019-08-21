package com.hcl.bankingapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.bankingapp.entity.Account;
import com.hcl.bankingapp.entity.Transactions;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.exception.BankException;
import com.hcl.bankingapp.exception.ErrorResponse;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.TransactionsRepository;
import com.hcl.bankingapp.service.BankTransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class BankTransactionServiceImplTest {

	@InjectMocks
	BankTransactionServiceImpl bankTransactionServiceImpl;
	@Mock
	TransactionsRepository transactionsRepository;
	@Mock
	AccountRepository accountRepository;

	@Mock
	AccountRepository accountRepository2;
	Account account;
	Account account2;

	UserDetails userDetails;
	Transactions transactions;

	List<Account> accountlist;
	List<UserDetails> userDetailslist;

	@Before
	public void setup() {

		userDetails = new UserDetails();
		userDetails.setUserId(2L);
		userDetails.setEmail("sai");
		userDetails.setMobile(99999L);
		userDetails.setPassword("1234");

		account = new Account();
		account.setAccountId(1L);
		account.setAccountNo(9999L);
		account.setBalance(10000D);
		account.setUserDetails(userDetails);

		account2 = new Account();
		account2.setAccountId(2L);
		account2.setAccountNo(9999L);
		account2.setBalance(10000D);
		account2.setUserDetails(userDetails);

		accountlist = new ArrayList<>();
		accountlist.add(account);

		userDetailslist = new ArrayList<>();
		userDetailslist.add(userDetails);

		transactions = new Transactions();
		transactions.setAmount(1000d); 
		transactions.setDate(LocalDate.now());
		transactions.setFromAccount(account.getAccountId());
		transactions.setToAccount(account2.getAccountId());
		transactions.setTransactionId(3L);
	}

	@Test(expected = BankException.class)
	public void transactionUserNotExistTest() {
//		Mockito.when(accountRepository.findByAccountNo(account.getAccountId())).thenReturn(accountlist);
//		Mockito.when(accountRepository2.findByAccountNo(account2.getAccountId())).thenReturn(accountlist);
//
//		Mockito.when(Mockito.when(transactionsRepository.save(transactions)).thenReturn(transactions));
//		Mockito.when(Mockito.when(accountRepository.save(account)).thenReturn(account));
//		Mockito.when(Mockito.when(accountRepository2.save(account2)).thenReturn(account2));
		ResponseEntity<ErrorResponse> actualValues = bankTransactionServiceImpl.transaction(account.getAccountId(),
				account2.getAccountId(), 100D);

		Assert.assertEquals("succsess", actualValues.getBody().getMessage());

	}

	
	@Test
	public void transactionTest() {
		Mockito.when(accountRepository.findByAccountNo(account.getAccountId())).thenReturn(accountlist);
		Mockito.when(accountRepository2.findByAccountNo(account2.getAccountId())).thenReturn(accountlist);

		ResponseEntity<ErrorResponse> actualValues = bankTransactionServiceImpl.transaction(account.getAccountId(),
				account2.getAccountId(), 100D);

		Assert.assertEquals("succsess", actualValues.getBody().getMessage());

	}
	
	
	@Test(expected = BankException.class)
	public void transactionNoUserTest() {
		Mockito.when(accountRepository.findByAccountNo(account.getAccountId())).thenReturn(accountlist);
//		Mockito.when(accountRepository2.findByAccountNo(account2.getAccountId())).thenReturn(accountlist);

		ResponseEntity<ErrorResponse> actualValues = bankTransactionServiceImpl.transaction(account.getAccountId(),
				account2.getAccountId(), 100D);

		Assert.assertEquals("succsess", actualValues.getBody().getMessage());

	} 
	
	
	@Test(expected = BankException.class)
	public void transactionBalenceNegatveTest() {
		
		Mockito.when(accountRepository.findByAccountNo(account.getAccountId())).thenReturn(accountlist);
		Mockito.when(accountRepository2.findByAccountNo(account2.getAccountId())).thenReturn(accountlist);

		ResponseEntity<ErrorResponse> actualValues = bankTransactionServiceImpl.transaction(account.getAccountId(),
				account2.getAccountId(), 100000d);

		Assert.assertEquals("succsess", actualValues.getBody().getMessage());
 
	} 
 
}
