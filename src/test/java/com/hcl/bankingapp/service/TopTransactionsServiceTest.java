package com.hcl.bankingapp.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.bankingapp.entity.Transactions;
import com.hcl.bankingapp.repository.TransactionsRepository;


@RunWith(MockitoJUnitRunner.class)
public class TopTransactionsServiceTest {
	
	@Mock
	TransactionsRepository transactionsRepository;
	
	@InjectMocks
	TopTransactionsService topTransactionsService;
	

	@Test
	public void testTopTransactions() {
		
		List<Transactions> transactionsList=new ArrayList<Transactions>();
		
		Transactions transactions=new Transactions();
		transactions.setAmount(1000D);
		transactions.setDate(LocalDate.now());
		transactions.setFromAccount(1234L);
		transactions.setTransactionId(1L);
		transactions.setToAccount(456L);
		
		transactionsList.add(transactions);
		
		Long accNumber=1234L;
		Mockito.when(transactionsRepository.getTransactionsByFromAccount(accNumber)).thenReturn(transactionsList);
		
		List<Transactions> actualValue=topTransactionsService.topTransactions(accNumber);
		
		assertEquals(1, actualValue.size());
		
	}

}
