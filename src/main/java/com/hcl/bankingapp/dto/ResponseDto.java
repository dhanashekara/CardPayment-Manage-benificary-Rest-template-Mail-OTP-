package com.hcl.bankingapp.dto;

public class ResponseDto {
	
	private String email;
	private String message;
	private Long accNo;
	private long userId;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getAccNo() {
		return accNo;
	}
	public void setAccNo(Long accNo) {
		this.accNo = accNo;
	}

}
