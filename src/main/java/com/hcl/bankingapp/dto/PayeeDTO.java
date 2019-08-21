package com.hcl.bankingapp.dto;

import java.io.Serializable;

public class PayeeDTO implements Serializable{
	private Long accountNo;
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	
	
}
