package com.ty.SpringBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.SpringBank.model.Account;
import com.ty.SpringBank.model.Transaction;
import com.ty.SpringBank.service.AccountService;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService service;

	@GetMapping("/create")
	public String showCreateAccount(Model model) {
		model.addAttribute("account", new Account());
		return "create-account";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute Account account) {
		service.save(account);

		return "redirect:/accounts/create";
	}

	@GetMapping("/deposit")
	public String depositPage() {
		return "deposit";
	}

	@PostMapping("/deposit")
	public String deposit(@RequestParam String accountNumber, @RequestParam double amount) {
		System.out.println(accountNumber);
		System.out.println(amount);

		service.deposit(accountNumber, amount);
		return "redirect:/accounts/deposit";
	}

	@GetMapping("/withdraw")
	public String withdrawPage() {
		return "withdraw";
	}

	@PostMapping("/withdraw")
	public String withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
		System.out.println(accountNumber);
		System.out.println(amount);

		service.withdraw(accountNumber, amount);
		return "redirect:/accounts/withdraw";
	}

	@GetMapping("/getTransaction")
	public String getTransaction() {
		return "getTransaction";

	}

	@PostMapping("/transactionHistory")
	public String transactionhistory(@RequestParam String accountNumber, Model model) {
		List<Transaction> transaction = service.getTransaction(accountNumber);
		System.out.println(transaction);
		model.addAttribute("transactions", transaction);
		return "transactionHistory";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/service")
	public String service() {
		return "service";
	}

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

}
