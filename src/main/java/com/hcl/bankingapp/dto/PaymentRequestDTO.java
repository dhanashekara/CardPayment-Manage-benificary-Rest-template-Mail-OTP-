package com.hcl.bankingapp.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
	private Long creditCardNumber;
	private String expDate;
	private double creditAmount;
	private int cvv;
}
