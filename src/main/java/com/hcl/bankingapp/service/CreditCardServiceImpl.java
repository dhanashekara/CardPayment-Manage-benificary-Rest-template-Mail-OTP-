package com.hcl.bankingapp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.bankingapp.dto.AccountDetailsDTO;
import com.hcl.bankingapp.dto.CreditCardTransactionDTO;
import com.hcl.bankingapp.dto.PaymentRequestDTO;
import com.hcl.bankingapp.dto.PaymentResDTO;
import com.hcl.bankingapp.entity.Account;
import com.hcl.bankingapp.entity.Authentication;
import com.hcl.bankingapp.entity.CreditCardDetails;
import com.hcl.bankingapp.entity.CreditCardTransaction;
import com.hcl.bankingapp.entity.UserDetails;
import com.hcl.bankingapp.exception.BankException;
import com.hcl.bankingapp.exception.EnterValidCredentials;
import com.hcl.bankingapp.exception.NoTransactions;
import com.hcl.bankingapp.exception.UserNotFound;
import com.hcl.bankingapp.repository.AccountRepository;
import com.hcl.bankingapp.repository.AuthenticationRepository;
import com.hcl.bankingapp.repository.CreditCardRepository;
import com.hcl.bankingapp.repository.CreditCardTransactionRepository;
import com.hcl.bankingapp.repository.UserDetailsRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private CreditCardTransactionRepository creditCardTransactionRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepo;

	@Autowired
	private OTPService otpService;

	@Autowired
	private AuthenticationRepository authRepo;
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private UserDetailsRepository userRepo;
	


	@Override
	public PaymentResDTO creditTransaction(PaymentRequestDTO paymentRequestDTO) {
		Optional<CreditCardDetails> creditCardDetails = creditCardRepository
				.findByCreditCardNumber(paymentRequestDTO.getCreditCardNumber());

		if (creditCardDetails.isPresent()) {
			CreditCardDetails creditD = creditCardDetails.get();

			Date date = creditD.getExpDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			String datee = calendar.get(Calendar.YEAR) + "";
			System.out.println("check the date   " + datee);
			String checkDate = paymentRequestDTO.getExpDate().split("/")[1];

			//System.out.println("check the date   " + checkDate);

			if (creditD.getCreditBalance() > paymentRequestDTO.getCreditAmount()
					&& creditD.getCvv() == paymentRequestDTO.getCvv() && datee.equals(checkDate)) {
				//System.out.println("inside ");
				double afterDeduction = creditD.getCreditBalance() - paymentRequestDTO.getCreditAmount();
				CreditCardDetails afterDeductionDetails = new CreditCardDetails();
				// after deduction
				afterDeductionDetails.setCreditBalance(afterDeduction);

				// to trasactions details

				CreditCardTransaction creditCardTransaction = new CreditCardTransaction();
				creditCardTransaction.setCreditCardDetails(afterDeductionDetails);
				creditCardTransaction.setDebitAmount(paymentRequestDTO.getCreditAmount());
				creditCardTransaction.setFromAccountNumber(paymentRequestDTO.getCreditCardNumber());
				creditCardTransaction.setPaymentType("credit_pay");
				creditCardTransaction.setTransactionDate(LocalDate.now());
				creditCardTransaction.setDescription("Debited from Creditcard");
				creditCardTransaction.setToAccountNumber(98765432L);
				creditCardRepository.save(afterDeductionDetails);

				creditCardTransactionRepository.save(creditCardTransaction);
				PaymentResDTO paymentResDTO = new PaymentResDTO();
				paymentResDTO.setMessage("Payment Successfull");
				return paymentResDTO;

			} else {
				throw new EnterValidCredentials("Please Enter Valid Credetials");

			}

		} else {
			throw new UserNotFound("Invalid User");
		}
	}

	@Override
	public Integer generateOTPForCredit(long userId) {

		UserDetails userDetails = userDetailsRepo.findByUserId(userId);
		Authentication auth = new Authentication();

		if (null != userDetails) {

			CreditCardDetails creditCard = creditCardRepository.findByUserId(userId);

			if (null != creditCard) {
				Integer otp = otpService.generateOTPandSendMail(userDetails.getEmail());

				if (otp != null) {
					auth.setOtp(otp);
					authRepo.save(auth);
				}
				return auth.getOtpGen();
			} else {
				throw new BankException("Card Number doesnot Exit");
			}
		} else {
			throw new UserNotFound("User Not Found");
		}

	}

	@Override
	public ResponseEntity<String> authenticatePayee(int otpGenId, int otp, PaymentRequestDTO paymentRequestDTO) {

		Authentication auth = authRepo.findByOtp(otp);
		RestTemplate restTemplate = new RestTemplate();
		if (auth.getOtp() == otp && auth.getOtpGen() == otpGenId) {
			// PayeeResDto payeeResDto = new PayeeResDto();
			// payeeResDto.setOtpGen(otpGenId);
			// payeeResDto.setMessage("Success");
			System.out.println("before rest");
			ResponseEntity<String> respStr =  restTemplate.postForEntity("http://192.168.43.179:8087/bank/user/credit", paymentRequestDTO,
					String.class);
			return respStr;

		} else {
			throw new EnterValidCredentials("Please enter valid credetials");
		}
	}
	
	

	public AccountDetailsDTO getAccountDetails(Long userId) {

		if (null != userId) {

			AccountDetailsDTO accountDTO = new AccountDetailsDTO();
			UserDetails userDetails = new UserDetails();
			userDetails.setUserId(userId);
		//	Account acc = new Account();
		//	acc.setUserDetails(userDetails);
			Account acc = accRepo.findByUserId(userId);
			userDetails = userRepo.findById(userId).get();
			if (null != acc && userDetails!= null) {
				CreditCardDetails crdDetails = creditCardRepository.findByUserDetails(userDetails);
				if(null != crdDetails) {
					accountDTO.setAccountbalance(acc.getBalance());
					accountDTO.setAccountMail(userDetails.getEmail());
					accountDTO.setAccountNo(acc.getAccountNo());
					accountDTO.setUserName(userDetails.getUserName());
					accountDTO.setCreditBalance(crdDetails.getCreditBalance());
					return accountDTO;
				}else {
					throw new UserNotFound("Credit Card details Not found");
				}
			}else {
				throw new UserNotFound("Account Not Found");
			}
		}else {
			throw new UserNotFound("User Not Found");
		}
	}

	public List<CreditCardTransactionDTO> getStatement(Long userId) {

		if(null != userId) {
			List<CreditCardTransaction> trList = creditCardTransactionRepository.getTransactionDetails(userId);
			
			
			if(null != trList && !trList.isEmpty()) {
				
				CreditCardTransactionDTO credDTO = new CreditCardTransactionDTO();
				List<CreditCardTransactionDTO> trDTOList = new ArrayList<>();
				
				trList.forEach(tr -> {
					
					credDTO.setAccountNumber(tr.getToAccountNumber());
					credDTO.setPaymentType(tr.getPaymentType());
					credDTO.setReceiverName("Amazon");
					credDTO.setDebitAmount(tr.getDebitAmount());
					credDTO.setDescription(tr.getDescription());
					credDTO.setTransactionDate(tr.getTransactionDate());
					trDTOList.add(credDTO);
				});
				return trDTOList;
			}else {
				throw new NoTransactions("No transaction Present");
			}
		}else {
			throw new UserNotFound("User Not Found");
		}
	}

}
