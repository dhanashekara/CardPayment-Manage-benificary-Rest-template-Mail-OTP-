package com.hcl.bankingapp.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class CreditCardTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	private double debitAmount;
	private Long fromAccountNumber;
	private Long toAccountNumber;
	private LocalDate transactionDate;
	private String description;
	private String paymentType;
	private String receiverName;

	@ManyToOne
	@JoinColumn(name = "creditCardId", referencedColumnName = "id")
	CreditCardDetails creditCardDetails;
}
