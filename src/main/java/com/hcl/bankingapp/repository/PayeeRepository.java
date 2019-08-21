package com.hcl.bankingapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.bankingapp.entity.PayeeDetails;
@Repository
public interface PayeeRepository extends JpaRepository<PayeeDetails, Long> {
	
	



	Optional<PayeeDetails> findByPayeeId(Long payeeId);
	

	@Query("select p from PayeeDetails p where p.userDetails.userId=:userId  order by p.payeeId DESC")
	List<PayeeDetails> findPayeeByUserId(Long userId);

}
