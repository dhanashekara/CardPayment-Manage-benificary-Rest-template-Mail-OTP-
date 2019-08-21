package com.hcl.bankingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.bankingapp.entity.Transactions;
import com.hcl.bankingapp.exception.NoTransactions;
import com.hcl.bankingapp.repository.TopTransactionsRepository;
import com.hcl.bankingapp.repository.TransactionsRepository;

@Service
public class TopTransactionsService {

	@Autowired
	TopTransactionsRepository topTransactionsRepository;

	@Autowired
	TransactionsRepository transactionsRepository;

	public List<Transactions> topTransactions(Long accNumber) {

		List<Transactions> transaction = transactionsRepository.getTransactionsByFromAccount(accNumber);

		if (transaction.isEmpty() == false)

			return transaction;

		else

			throw new NoTransactions("No transactions available");

	}

}
