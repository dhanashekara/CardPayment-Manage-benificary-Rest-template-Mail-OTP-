package com.hcl.bankingapp.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingapp.exception.BankException;
import com.hcl.bankingapp.utility.MailWithOTPService;

@Service
public class OTPService {

	@Autowired
	MailWithOTPService mailWithOTPService;

	public Integer generateOTPandSendMail(String email, Long payeeAccountNumber) {
		Integer otp = 0;
		try {
			Random rand = new Random();
			otp = 1000 + rand.nextInt(9999);
			String body = "OTP for Adding Payee with Account number " + payeeAccountNumber + " is " + otp;
			String subject = "ING Bank Transactions";
			mailWithOTPService.sendEmail(email, subject, body);

		} catch (Exception e) {
			// throw new
		}
		return otp;
	}

	public Integer generateOTPandSendMail(String email) {
		Integer otp = 0;
		try {
			Random rand = new Random();
			otp = 1000 + rand.nextInt(9999);
			String body = "OTP for Credit Card Payment is " + otp;
			String subject = "ING Bank Transactions";
			mailWithOTPService.sendEmail(email, subject, body);

		} catch (Exception e) {
			throw new BankException("Error in generating OTP");
		}
		return otp;
	}
}