package com.hcl.bankingapp.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.bankingapp.dto.LoginDto;
import com.hcl.bankingapp.dto.ResponseDto;
import com.hcl.bankingapp.entity.Account;
import com.hcl.bankingapp.entity.Transactions;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.exception.EnterValidCredentials;
import com.hcl.bankingapp.exception.UserNotFound;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.TransactionsRepository;
import com.hcl.bankingapp.repository.UserDetailsRepository;
import com.hcl.bankingapp.service.UserLoginServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserLoginServiceImplTest {

	@Mock
	UserDetailsRepository userDetailsRepository;

	@Mock
	AccountRepository accountRepository;

	@Mock
	TransactionsRepository transactionsRepository;

	@InjectMocks
	UserLoginServiceImpl userLoginService;

	@Before
	public void setup() {

	}

	@Test
	public void testUserLogin() {
		LoginDto loginDto = new LoginDto();
		loginDto.setEmail("raja");
		loginDto.setPassword("raja@123");
		
		UserDetails user = new UserDetails();
		user.setUserId(1L);
		user.setEmail("raja");
		user.setPassword("raja@123");
		user.setMobile(9030853295L);

		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNo(1234L);
		account.setBalance(1232345D);
		account.setUserDetails(user);

		List<Transactions> transactions = new ArrayList<>();
		Transactions transaction = new Transactions();
		transaction.setTransactionId(1L);
		transaction.setFromAccount(123L);
		transaction.setToAccount(456L);
		transaction.setDate(LocalDate.now()); 

		transactions.add(transaction);
		Transactions transaction1 = new Transactions();

		transaction1.setTransactionId(1L);
		transaction1.setFromAccount(123L);
		transaction1.setToAccount(456L);
		transaction1.setDate(LocalDate.now());
		transactions.add(transaction1);
		
		List<Account> accountList=new ArrayList<>();
		accountList.add(account);
		
		List<UserDetails> UserDetailsList=new ArrayList<>();
		UserDetailsList.add(user);
 
		Mockito.when(userDetailsRepository.findByEmail(user.getEmail())).thenReturn(user);

		Mockito.when(accountRepository.findByUserDetails(user)).thenReturn(accountList);

//		Mockito.when(transactionsRepository.getTransactionsByFromAccount(account.getAccountNo()))
//				.thenReturn(transactions);
		
		ResponseDto responseDto=new ResponseDto();
		responseDto.setAccNo(1234L);
		responseDto.setEmail(user.getEmail());
		responseDto.setMessage("User registration done successfully");
		 

		ResponseDto actualValue = userLoginService.userLogin(loginDto);
		assertEquals(responseDto.getAccNo(),actualValue.getAccNo());
	}

	@Test(expected = EnterValidCredentials.class)
	public void testUserLoginSecondNegitiveCase() {
		LoginDto loginDto = new LoginDto();
		loginDto.setEmail("raja");
		loginDto.setPassword("rajaaaa@123");
		UserDetails user = new UserDetails();
		user.setUserId(1L);
		user.setEmail("raja");
		user.setPassword("raja@123");
		user.setMobile(9030853295L);

		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNo(1234L);
		account.setBalance(1232345D);
		account.setUserDetails(user);

		List<Transactions> transactions = new ArrayList<>();
		Transactions transaction = new Transactions();
		transaction.setTransactionId(1L);
		transaction.setFromAccount(123L);
		transaction.setToAccount(456L);
		transaction.setDate(LocalDate.now());

		transactions.add(transaction);
		Transactions transaction1 = new Transactions();

		transaction1.setTransactionId(1L);
		transaction1.setFromAccount(123L);
		transaction1.setToAccount(456L);
		transaction1.setDate(LocalDate.now());
		transactions.add(transaction1);

		Mockito.when(userDetailsRepository.findByEmail("raja")).thenReturn(user);


		ResponseDto responseDto=new ResponseDto();
		responseDto.setAccNo(123L);
		responseDto.setEmail(user.getEmail());
		responseDto.setMessage("User registration done successfully");
		
		ResponseDto actualValue = userLoginService.userLogin(loginDto);
	}

	@Test(expected = UserNotFound.class)
	public void testUserLoginNegitiveCase() {
		LoginDto loginDto = new LoginDto();
		loginDto.setEmail("raja");
		loginDto.setPassword("raja@123");
		UserDetails user = new UserDetails();
		user.setUserId(1L);
		user.setEmail("raja");
		user.setPassword("raja@123");
		user.setMobile(9030853295L);

		Account account = new Account();
		account.setAccountId(1L);
		account.setAccountNo(1234L);
		account.setBalance(1232345D);
		account.setUserDetails(user);

		List<Transactions> transactions = new ArrayList<>();
		Transactions transaction = new Transactions();
		transaction.setTransactionId(1L);
		transaction.setFromAccount(123L);
		transaction.setToAccount(456L);
		transaction.setDate(LocalDate.now());

		transactions.add(transaction);
		Transactions transaction1 = new Transactions();

		transaction1.setTransactionId(1L);
		transaction1.setFromAccount(123L);
		transaction1.setToAccount(456L);
		transaction1.setDate(LocalDate.now());
		transactions.add(transaction1);
		

		ResponseDto responseDto=new ResponseDto();
		responseDto.setAccNo(123L);
		responseDto.setEmail(user.getEmail());
		responseDto.setMessage("User registration done successfully");
		

		// Mockito.when(userDetailsRepository.findByUserName("Arjun")).thenReturn(user);

		ResponseDto actualValue = userLoginService.userLogin(loginDto);

	}

}
