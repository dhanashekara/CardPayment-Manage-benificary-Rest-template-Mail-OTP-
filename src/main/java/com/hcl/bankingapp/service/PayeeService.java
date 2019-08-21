package com.hcl.bankingapp.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.hcl.bankingapp.dto.PayeeReqDto;
import com.hcl.bankingapp.dto.PayeeResDto;
import com.hcl.bankingapp.dto.PayeeUpdateReqDto;
import com.hcl.bankingapp.entity.PayeeDetails;

public interface PayeeService {

	PayeeResDto addPayee(PayeeReqDto payeeReqDto) ;

	PayeeResDto updatePayee(PayeeUpdateReqDto payeeUpdateReqDto);

	List<PayeeDetails> getAllPayee(Long userId);

	Integer generateOTP(String email, Long payeeAccountNumber);

	PayeeResDto deletePayee(long payeeId);

	PayeeResDto authenticatePayee(int otpGenId, int otp);

	

//	int generateOTPForCredit(String email, Long payeeAccountNumber);

	//Integer generateOTPForCredit(long userId);

}
