package com.ty.SpringBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.SpringBank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByAccountNumber(String accountNumber);

}
