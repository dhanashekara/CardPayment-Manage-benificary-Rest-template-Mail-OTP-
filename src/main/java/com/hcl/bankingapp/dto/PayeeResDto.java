package com.hcl.bankingapp.dto;

public class PayeeResDto {
	
	private Long payeeId;
	private String message;
	private int otpGen;
	
	
	public int getOtpGen() {
		return otpGen;
	}
	public void setOtpGen(int otpGen) {
		this.otpGen = otpGen;
	}
	public Long getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
