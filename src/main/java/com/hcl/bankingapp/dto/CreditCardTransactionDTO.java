package com.hcl.bankingapp.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardTransactionDTO {

	private Long accountNumber;
	private String receiverName;
	private String paymentType;
	
	private LocalDate transactionDate;
	private String description;
	private double debitAmount;
	
}