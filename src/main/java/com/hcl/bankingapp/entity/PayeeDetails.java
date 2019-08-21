package com.hcl.bankingapp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PayeeDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long payeeId;
	private String payeeName;

	private String branchName;

	private String ifsc;
	private Long accountNo;

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	private String status;
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserDetails userDetails;

	public Long getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(Long payeeId) {
		this.payeeId = payeeId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public PayeeDetails() {
		// TODO Auto-generated constructor stub
	}

}
