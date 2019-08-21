package com.hcl.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingapp.dto.LoginDto;
import com.hcl.bankingapp.dto.ResponseDto;
import com.hcl.bankingapp.service.UserLoginService;

@CrossOrigin(allowedHeaders= {"*","*/"},origins= {"*","*/"})
@RestController
public class UserLoginController {

	@Autowired
	UserLoginService userLoginService;

	@PostMapping("/user/login")
	public ResponseEntity<ResponseDto> userLogin(@RequestBody LoginDto loginDto) {

		return new ResponseEntity<>(userLoginService.userLogin(loginDto), HttpStatus.OK);
	}

}
