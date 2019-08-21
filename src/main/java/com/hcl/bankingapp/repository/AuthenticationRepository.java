package com.hcl.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bankingapp.entity.Authentication;
@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {

	Authentication findByOtp(int otp);

}
