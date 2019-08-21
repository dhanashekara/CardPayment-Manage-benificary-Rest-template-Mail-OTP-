package com.hcl.bankingapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.bankingapp.entity.CreditCardDetails;
import com.hcl.bankingapp.entity.UserDetails;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardDetails, Long> {

	Optional<CreditCardDetails> findByCreditCardNumber(Long creditCardNumber);

	@Query(value = "SELECT * FROM bankingapp.credit_card_details WHERE user_id =?1", nativeQuery = true)
	CreditCardDetails findByUserId(long userId);

	public CreditCardDetails findByUserDetails(UserDetails userDetails);
}
