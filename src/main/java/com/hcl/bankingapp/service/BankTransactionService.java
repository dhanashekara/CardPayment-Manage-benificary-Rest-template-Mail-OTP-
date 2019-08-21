package com.hcl.bankingapp.service;

import org.springframework.http.ResponseEntity;

import com.hcl.bankingapp.exception.ErrorResponse;

public interface BankTransactionService {
	
	public ResponseEntity<ErrorResponse> transaction(Long fromaccount , Long toaccount, Double amount);

}
