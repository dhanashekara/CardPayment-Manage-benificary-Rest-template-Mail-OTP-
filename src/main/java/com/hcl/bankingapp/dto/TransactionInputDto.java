package com.hcl.bankingapp.dto;

public class TransactionInputDto {

	/**
	 * 
	 */
	
	private Long fromaccount ;
	private  Long toaccount;
	private Double amount;
	
	
	public Long getFromaccount() {
		return fromaccount;
	}
	public void setFromaccount(Long fromaccount) {
		this.fromaccount = fromaccount;
	}
	public Long getToaccount() {
		return toaccount;
	}
	public void setToaccount(Long toaccount) {
		this.toaccount = toaccount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	

}
