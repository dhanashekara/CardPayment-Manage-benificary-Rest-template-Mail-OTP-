package com.hcl.bankingapp.dto;

import java.io.Serializable;

public class UserResponseDTO implements Serializable {

	private Long accno;

	private String message;

	public Long getAccno() { 
		return accno;
	}

	public void setAccno(Long accno) {
		this.accno = accno;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}