package com.hcl.bankingapp.dto;

public class PayeeUpdateReqDto {

	private Long payeeId;

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
	}

	private String branchName;

	private String ifsc;
	private String email;
	private Long accountNo;

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
}
