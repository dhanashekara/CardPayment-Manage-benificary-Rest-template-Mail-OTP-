package com.hcl.bankingapp.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transactions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long transactionId;
	private Long fromAccount;
	private Long toAccount;
	private  Double amount;
	private LocalDate date;
	private String transactionType;
	private String description;
	
	
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Long getToAccount() {
		return toAccount;
	}
	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Transactions() {
		// TODO Auto-generated constructor stub
	}

}
