package com.hcl.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.bankingapp.entity.Transactions;
import com.hcl.bankingapp.service.TopTransactionsService;

@CrossOrigin(allowedHeaders= {"*","*/"},origins= {"*","*/"})
@RestController
public class TopTransactionsController {
	
	@Autowired
	TopTransactionsService topTransactionsService;
	
	@GetMapping("/transactions/{accNumber}")
	public ResponseEntity<List<Transactions>> topTransactions(@PathVariable Long accNumber){
		
		return new ResponseEntity<>(topTransactionsService.topTransactions(accNumber),HttpStatus.OK);
		
	}
	

}
