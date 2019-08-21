package com.hcl.bankingapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.hcl.bankingapp.dto.AccountDetailsDTO;
import com.hcl.bankingapp.dto.CreditCardTransactionDTO;
import com.hcl.bankingapp.dto.PaymentRequestDTO;
import com.hcl.bankingapp.dto.PaymentResDTO;

public interface CreditCardService {

	PaymentResDTO creditTransaction(PaymentRequestDTO paymentRequestDTO);

	Integer generateOTPForCredit(long userId);

	ResponseEntity<String> authenticatePayee(int otpGenId, int otp,PaymentRequestDTO paymentRequestDTO);
	AccountDetailsDTO getAccountDetails(Long userId);

	List<CreditCardTransactionDTO> getStatement(Long userId);


}
