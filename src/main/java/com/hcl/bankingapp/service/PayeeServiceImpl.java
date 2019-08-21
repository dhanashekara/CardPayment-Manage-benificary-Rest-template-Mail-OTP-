package com.hcl.bankingapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingapp.dto.PayeeReqDto;
import com.hcl.bankingapp.dto.PayeeResDto;
import com.hcl.bankingapp.dto.PayeeUpdateReqDto;
import com.hcl.bankingapp.entity.Authentication;
import com.hcl.bankingapp.entity.PayeeDetails;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.exception.EnterValidCredentials;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.AuthenticationRepository;
import com.hcl.bankingapp.repository.PayeeRepository;
import com.hcl.bankingapp.repository.UserDetailsRepository;

@Service
public class PayeeServiceImpl implements PayeeService {

	@Autowired
	private PayeeRepository payeeRepository;
	@Autowired
	UserDetailsRepository userDetailsRepository;
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Autowired
	OTPService otpService;

	@Autowired
	AuthenticationRepository authRepo;
	
	@Override
	public PayeeResDto addPayee(PayeeReqDto payeeReqDto) {

		PayeeDetails payeeDetails = new PayeeDetails();

		payeeDetails.setPayeeName(payeeReqDto.getPayeeName());
		payeeDetails.setAccountNo(payeeReqDto.getAccountNo());
		payeeDetails.setBranchName(payeeReqDto.getBranchName());
		payeeDetails.setIfsc(payeeReqDto.getIfsc());

		UserDetails userDetails = userDetailsRepository.findByUserId(payeeReqDto.getUserId());

		if (userDetails != null) {
			payeeDetails.setUserDetails(userDetails);
			payeeDetails.setStatus("Pending");

			PayeeDetails payeeRes = payeeRepository.save(payeeDetails);
			PayeeResDto payeeResDto = new PayeeResDto();
			payeeResDto.setPayeeId(payeeRes.getPayeeId());
			payeeResDto.setMessage("Success");

			return payeeResDto;
		} else {
			throw new EnterValidCredentials("Please enter valid credetials");
		}
	}

	@Override
	public PayeeResDto updatePayee(PayeeUpdateReqDto payeeUpdateReqDto) {
		Optional<PayeeDetails> payeeDetails=payeeRepository.findByPayeeId(payeeUpdateReqDto.getPayeeId());
		if(payeeDetails.isPresent()) {
			PayeeDetails paye=payeeDetails.get();
			paye.setBranchName(payeeUpdateReqDto.getBranchName());
			paye.setIfsc(payeeUpdateReqDto.getIfsc());
			PayeeResDto payeeResDto = new PayeeResDto();
			payeeRepository.save(paye);
			payeeResDto.setPayeeId(paye.getPayeeId());
			payeeResDto.setMessage("Success");
			return payeeResDto;

		}
		else {
		throw new EnterValidCredentials("Please enter valid credetials");
		}
	}

	@Override
	public List<PayeeDetails> getAllPayee(Long userId) {
		
		return payeeRepository.findPayeeByUserId(userId);
	}
	
	
	@Override
	public PayeeResDto deletePayee(long payeeId) {
		// TODO Auto-generated method stub
		 payeeRepository.deleteById(payeeId);
		PayeeResDto payeeResDto = new PayeeResDto();
		payeeResDto.setPayeeId(payeeId);
		payeeResDto.setMessage("Success");
		return payeeResDto;
		
	}
	
	
	
public Integer generateOTP(String email,Long payeeAccountNumber) {
		
		Authentication auth = new Authentication();
		
		if(null != email && !email.isEmpty()) {
			Integer otp = otpService.generateOTPandSendMail(email,payeeAccountNumber);
			if(otp != null) {
				auth.setOtp(otp);
				authRepo.save(auth);
			}
		}
		return auth.getOtpGen();
	}

@Override
	public PayeeResDto authenticatePayee(int otpGenId, int otp) {
	Authentication auth=authenticationRepository.findByOtp(otp);
	if (auth.getOtp()==otp && auth.getOtpGen()==otpGenId) {
		PayeeResDto payeeResDto = new PayeeResDto();
		//payeeResDto.setOtpGen(otpGenId);
		//payeeResDto.setMessage("Success");
		
		return payeeResDto;
		
	}else {
		throw new EnterValidCredentials("Please enter valid credetials");
		}
	
}



}
