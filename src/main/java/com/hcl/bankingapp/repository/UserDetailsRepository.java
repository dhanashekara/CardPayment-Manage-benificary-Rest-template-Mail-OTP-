package com.hcl.bankingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.bankingapp.entity.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	

	public UserDetails findByEmail(String userName);

	public UserDetails findByUserId(Long userId);

}
