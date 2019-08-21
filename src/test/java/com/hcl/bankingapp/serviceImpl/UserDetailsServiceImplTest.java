package com.hcl.bankingapp.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcl.bankingapp.dto.LoginDto;
import com.hcl.bankingapp.dto.PayeeDTO;
import com.hcl.bankingapp.entity.Account;
import com.hcl.bankingapp.entity.Transactions;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.UserDetailsRepository;
import com.hcl.bankingapp.service.UserDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {
	
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Mock
	AccountRepository accountRepository;
	@Mock
	UserDetailsRepository userDetailsRepository;
	
	
	
	
	@Test
	public void getAllPayeeDetails() {
		

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
		
		List<UserDetails> userDetailsList=new ArrayList<UserDetails>();
		userDetailsList.add(user);

		
		List<Account> accountList=new ArrayList<Account>();
		accountList.add(account);

 
		 
	Mockito.when(userDetailsRepository.findAll()).thenReturn(userDetailsList);
		
//	Mockito.when(accountRepository.findByUserDetails(user)).thenReturn(accountList);
	
	List<PayeeDTO> actulValu = userDetailsServiceImpl.getAllPayeeDetails(user.getUserId());
	Assert.assertEquals(0, actulValu.size());
	
	}

}
