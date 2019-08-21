package com.hcl.bankingapp.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@NoArgsConstructor
@Data
public class CreditCardDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long creditCardNumber;
	private Date expDate;
	private double creditBalance;
	private String creditCardName;
	private int cvv;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	UserDetails userDetails;
	
}