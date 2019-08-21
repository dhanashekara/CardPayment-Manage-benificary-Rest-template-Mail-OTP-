package com.hcl.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bankingapp.entity.Transactions;

@Repository
public interface TopTransactionsRepository extends JpaRepository<Transactions, Long> {
	

}
