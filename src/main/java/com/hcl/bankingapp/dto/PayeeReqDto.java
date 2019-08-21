package com.hcl.bankingapp.dto;

public class PayeeReqDto {

	private String payeeName;
	private Long accountNo;
	private String branchName;
	private String ifsc;
	private String email;
	private Long userId;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	
	
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
