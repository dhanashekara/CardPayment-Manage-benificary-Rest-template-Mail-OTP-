package com.hcl.bankingapp.service;

import java.util.List;

import com.hcl.bankingapp.dto.PayeeDTO;

public interface UserDetailsService {
	public List<PayeeDTO> getAllPayeeDetails(Long userId);
}
