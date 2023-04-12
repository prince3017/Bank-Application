package com.example.BankApp.Controller;

import com.example.BankApp.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/credit")
    public ResponseEntity<String> credit(@RequestParam("user_id") String user_id,
                                         @RequestParam("account_id") Long account_id,
                                         @RequestParam("amount") int amount) {
        try {
            transactionService.credit(user_id, account_id, amount);
            return new ResponseEntity<>("Credit operation successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/debit")
    public ResponseEntity<String> debit(@RequestParam("user_id") String user_id,
                                        @RequestParam("account_id") Long account_id,
                                        @RequestParam("amount") int amount) {
        try {
            transactionService.debit(user_id, account_id, amount);
            return new ResponseEntity<>("Debit operation successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
