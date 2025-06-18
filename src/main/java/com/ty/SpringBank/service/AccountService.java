package com.ty.SpringBank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.SpringBank.model.Account;
import com.ty.SpringBank.model.Transaction;
import com.ty.SpringBank.repository.AccountRepository;
import com.ty.SpringBank.repository.TransactionRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	@Autowired
	private TransactionRepository transactionRepository;

	public Account save(Account account) {
		repository.save(account);
		return account;

	}

	// deposit
	public Account getAccountByNumber(String accountNumber) {

		return repository.findByAccountNumber(accountNumber);
	}

	public void deposit(String accountNumber, double amount) {

		Account account = repository.findByAccountNumber(accountNumber);
		account.setBalance(account.getBalance() + amount);
		repository.save(account);

		Transaction transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setAmount(amount);
		transaction.setType("DEPOSIT");
		transaction.setTimeStamp(new Date());
		transactionRepository.save(transaction);

	}

	// Withdraw
	public Account getAccountByAcNumber(String accountNumber) {

		return repository.findByAccountNumber(accountNumber);
	}

	public void withdraw(String accountNumber, double amount) {
		Account account = repository.findByAccountNumber(accountNumber);
		if (account.getBalance() >= amount) {

			account.setBalance(account.getBalance() - amount);
			repository.save(account);

			Transaction transaction = new Transaction();
			transaction.setAccount(account);
			transaction.setAmount(amount);
			transaction.setType("WITHDRAW");
			transaction.setTimeStamp(new Date());
			transactionRepository.save(transaction);
		} else {
			throw new RuntimeException("Insufficient Ammont");
		}

	}

	public List<Transaction> getTransaction(String accountNumber) {
		Account account = repository.findByAccountNumber(accountNumber);
		if (account != null) {
			return transactionRepository.findByAccountId(account.getId());
		} else {
			throw new RuntimeException("Invalid Account");
		}

	}

}
