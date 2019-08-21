package com.hcl.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.bankingapp.entity.CreditCardTransaction;

@Repository
public interface CreditCardTransactionRepository extends JpaRepository<CreditCardTransaction, Long>{

	@Query(value = "SELECT * from bankingapp.credit_card_transaction WHERE credit_card_id = "
			+ "(SELECT credit_card_id from bankingapp.credit_card_details WHERE user_id = ?1)",nativeQuery = true)
	List<CreditCardTransaction> getTransactionDetails(Long userId);
}