package com.hcl.bankingapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingapp.dto.PayeeDTO;
import com.hcl.bankingapp.entity.Account;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.UserDetailsRepository;
import com.hcl.bankingapp.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

@Autowired
UserDetailsRepository userDetailsRepository;

@Autowired 
AccountRepository accountRepository;

public List<PayeeDTO> getAllPayeeDetails(Long userId){
	List<PayeeDTO> listPayeeDTOs = new ArrayList<PayeeDTO>();
	List<UserDetails> listUserDetails = userDetailsRepository.findAll();
	for (UserDetails userDetails : listUserDetails) {
			if(!userDetails.getUserId().equals(userId)) {
			List<Account> accountList = accountRepository.findByUserDetails(userDetails);
			PayeeDTO payeeDTO = new PayeeDTO();
			payeeDTO.setAccountNo(accountList.get(0).getAccountNo());
			listPayeeDTOs.add(payeeDTO);
		}
	}
	return listPayeeDTOs;
}
}
