package com.hcl.bankingapp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDetailsDTO {

	private Long accountNo;
	private Double accountbalance;
	private Double creditBalance;
	private String accountMail;
	private String userName;
}