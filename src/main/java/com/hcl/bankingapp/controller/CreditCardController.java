package com.hcl.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingapp.dto.AccountDetailsDTO;
import com.hcl.bankingapp.dto.CreditCardTransactionDTO;
import com.hcl.bankingapp.dto.PaymentRequestDTO;
import com.hcl.bankingapp.dto.PaymentResDTO;
import com.hcl.bankingapp.service.CreditCardService;
import com.hcl.bankingapp.service.OTPService;
import com.hcl.bankingapp.service.PayeeService;

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class CreditCardController {
	
	@Autowired
   private CreditCardService creditCardService;
	
	@Autowired
	private PayeeService payeeService;
	@Autowired
	OTPService otpService;
	
	@PostMapping("/user/credit")
	public ResponseEntity<PaymentResDTO> creditTransaction(@RequestBody PaymentRequestDTO paymentRequestDTO){
		System.out.println("inside rest");
		PaymentResDTO result=creditCardService.creditTransaction(paymentRequestDTO);
		return new ResponseEntity<PaymentResDTO>(result, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate/{otpGenId}/{otp}")
	public ResponseEntity<String> authenticatePayment(@PathVariable int otpGenId, @PathVariable int otp,@RequestBody PaymentRequestDTO paymentRequestDTO) {
	//	creditCardService.authenticatePayee(otpGenId, otp,paymentRequestDTO);
		return creditCardService.authenticatePayee(otpGenId, otp,paymentRequestDTO);
	}
	
	@GetMapping("/user/{userId}/otp")
	public ResponseEntity<Integer> generateOTP(@PathVariable long userId){
		return new ResponseEntity<Integer>(creditCardService.generateOTPForCredit(userId),HttpStatus.OK);
	}
	
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<AccountDetailsDTO> getAccountDetails(@PathVariable Long userId) {

		return new ResponseEntity<AccountDetailsDTO>(creditCardService.getAccountDetails(userId), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/statement/")
	public ResponseEntity<List<CreditCardTransactionDTO>> getStatement(@PathVariable Long userId) {

		return new ResponseEntity<List<CreditCardTransactionDTO>>(creditCardService.getStatement(userId), HttpStatus.OK);
	}
	
}
