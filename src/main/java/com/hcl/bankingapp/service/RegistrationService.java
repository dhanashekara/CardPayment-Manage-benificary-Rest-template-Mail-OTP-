package com.hcl.bankingapp.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingapp.dto.UserDetailsDTO;
import com.hcl.bankingapp.entity.Account;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.exception.BankException;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.UserDetailsRepository;

@Service
public class RegistrationService {

	@Autowired
	UserDetailsRepository userdetailsrepository;

	@Autowired
	AccountRepository accountrepository;

	static long accno = 1000000;
	final Double balance = 20000.00;
 
	public long registerCustomer(UserDetailsDTO userDetailsDTO) {

		UserDetails details=new UserDetails();
		
		BeanUtils.copyProperties(userDetailsDTO, details);
		
		UserDetails users = userdetailsrepository.findByEmail(details.getEmail());
		if (users != null)
			throw new BankException(details.getEmail() + " already existed");

		UserDetails user = userdetailsrepository.save(details);
		accno++;
		Account acc = new Account();
		acc.setAccountNo(accno);
		acc.setBalance(balance);
		acc.setUserDetails(user);
		accountrepository.save(acc);
		return accno;

	}

	public List<UserDetails> getUsers() {
		return userdetailsrepository.findAll();
	}

	public List<Account> getAccountDetails() {
		return accountrepository.findAll();
	}

}

//here the save method is just declaration which is overriden in repository,
// that is being implemented from JPA Repository--->Repositroy