package com.hcl.bankingapp.exception;

public class EnterValidCredentials extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;

	public EnterValidCredentials(String message) {
		
		super(message);
	}

}
