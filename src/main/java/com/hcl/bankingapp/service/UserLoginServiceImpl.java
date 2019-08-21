package com.hcl.bankingapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingapp.dto.LoginDto;
import com.hcl.bankingapp.dto.ResponseDto;
import com.hcl.bankingapp.entity.Account;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.exception.EnterValidCredentials;
import com.hcl.bankingapp.exception.UserNotFound;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.TransactionsRepository;
import com.hcl.bankingapp.repository.UserDetailsRepository;

@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	private final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Autowired
	TransactionsRepository transactionsRepository;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public ResponseDto userLogin(LoginDto loginDto) {
		
		logger.info("LoginData:"+loginDto.getEmail()+"Password:"+loginDto.getPassword());

		
		List<Account> accountlist = null;
		Account account = null;
		Long userId = 0L;
		
		String userName= loginDto.getEmail();
		String password=loginDto.getPassword();

		UserDetails userDetails = userDetailsRepository.findByEmail(userName);

		if (userDetails != null) {

			if ((userDetails.getEmail().equals(userName) && userDetails.getPassword().equals(password))) {

				userId = userDetails.getUserId();

				accountlist = accountRepository.findByUserDetails(userDetails);
				ResponseDto responseDto=new ResponseDto();
				account=accountlist.get(0);
				responseDto.setAccNo(account.getAccountNo());
				responseDto.setMessage("user Login Succesfully");
				responseDto.setEmail(userName);
				responseDto.setUserId(userId);
				
				return responseDto;
				
			}

			else {
				throw new EnterValidCredentials("Please enter correct username or password");
			}
		} else {
			throw new UserNotFound("User does not exists");
		}

	}

}
