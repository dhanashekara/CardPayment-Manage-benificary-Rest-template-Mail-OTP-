package com.hcl.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpDTO {

	private int otpGenId;
	private int otp;
}
