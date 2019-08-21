package com.hcl.bankingapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResDTO {
	private String message;
	private int otpGen;
}
