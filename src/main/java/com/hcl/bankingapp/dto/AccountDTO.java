package com.hcl.bankingapp.dto;

import java.io.Serializable;

import com.hcl.bankingapp.entity.UserDetails;

public class AccountDTO implements Serializable {

	private Long accountId;
	private Long accountNo;
	private Double balance;

	UserDetails userDetails;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
