package com.hcl.bankingapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingapp.dto.PayeeReqDto;
import com.hcl.bankingapp.dto.PayeeResDto;
import com.hcl.bankingapp.dto.PayeeUpdateReqDto;
import com.hcl.bankingapp.entity.PayeeDetails;
import com.hcl.bankingapp.service.PayeeService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/user")
public class PayeeController {
	private final Logger logger = LoggerFactory.getLogger(PayeeController.class);

	@Autowired
	private PayeeService payeeService;

	@PostMapping("/save")
	public ResponseEntity<PayeeResDto> addPayee(@RequestBody PayeeReqDto payeeReqDto) {
		logger.info("Entered into PayeeController");
		int otpGenId = payeeService.generateOTP(payeeReqDto.getEmail(), payeeReqDto.getAccountNo());

		PayeeResDto payeeResDto = payeeService.addPayee(payeeReqDto);
		payeeResDto.setOtpGen(otpGenId);
		return new ResponseEntity<>(payeeResDto, HttpStatus.CREATED);

	}

	@PutMapping("/update")
	public ResponseEntity<PayeeResDto> updatePayee(@RequestBody PayeeUpdateReqDto payeeUpdateReqDto) {
		logger.info("Entered into update payee");
		int otpGenId = payeeService.generateOTP(payeeUpdateReqDto.getEmail(), payeeUpdateReqDto.getAccountNo());
		PayeeResDto payeeResDto = payeeService.updatePayee(payeeUpdateReqDto);
		payeeResDto.setOtpGen(otpGenId);

		return new ResponseEntity<>(payeeResDto, HttpStatus.OK);
	}

	@GetMapping("{userId}/payee")
	public ResponseEntity<List<PayeeDetails>> getAllPayee(@PathVariable Long userId) {
		logger.info("Entered into get all payee");
		List<PayeeDetails> payees = payeeService.getAllPayee(userId);
		return new ResponseEntity<>(payees, HttpStatus.OK);
	}

	@DeleteMapping("/payee/{payeeId}")
	public ResponseEntity<PayeeResDto> deletePayee(@PathVariable long payeeId, @RequestParam String email,
			@RequestParam Long payeeAccountNumber) {
		int otpGenId = payeeService.generateOTP(email, payeeAccountNumber);

		PayeeResDto payee = payeeService.deletePayee(payeeId);
		payee.setOtpGen(otpGenId);
		return new ResponseEntity<>(payee, HttpStatus.OK);

	}

	@GetMapping("/authenticate")
	public ResponseEntity<PayeeResDto> authenticatePayee(@RequestParam int otpGenId, @RequestParam int otp) {
		PayeeResDto result = payeeService.authenticatePayee(otpGenId, otp);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
