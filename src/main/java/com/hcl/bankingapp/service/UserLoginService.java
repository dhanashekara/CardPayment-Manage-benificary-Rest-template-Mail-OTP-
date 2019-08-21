package com.hcl.bankingapp.service;

import org.springframework.stereotype.Service;

import com.hcl.bankingapp.dto.LoginDto;
import com.hcl.bankingapp.dto.ResponseDto;

@Service
public interface UserLoginService {
	
	public ResponseDto userLogin(LoginDto loginDto);

}
