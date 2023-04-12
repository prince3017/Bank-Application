package com.example.BankApp.Service;

import com.example.BankApp.Model.Account;
import com.example.BankApp.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private AccountRepository accountRepository;

    public void credit(String user_id, Long account_id, int amount) throws Exception {
        Account account = accountRepository.findById(account_id).orElseThrow(() -> new Exception("Account not found"));
        if (account.getBalance() + amount > 10000000) {
            throw new Exception("Credit amount exceeds account balance limit");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void debit(String user_id, Long account_id, int amount) throws Exception {
        Account account = accountRepository.findById(account_id).orElseThrow(() -> new Exception("Account not found"));
        if (account.getBalance() - amount < 0) {
            throw new Exception("Debit amount exceeds account balance limit");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }
}
