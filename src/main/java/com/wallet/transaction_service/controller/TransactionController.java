package com.wallet.transaction_service.controller;

import com.wallet.transaction_service.model.Transaction;
import com.wallet.transaction_service.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request, Principal principal) {
        Transaction transaction = transactionService.transfer(principal.getName(), request.getToUser(), request.getAmount());
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/my")
    public List<Transaction> myTransactions(Principal principal) {
        return transactionService.getUserTransactions(principal.getName());
    }

    public static class TransferRequest {
        private String toUser;
        private Double amount;

        public String getToUser() { return toUser; }
        public void setToUser(String toUser) { this.toUser = toUser; }
        public Double getAmount() { return amount; }
        public void setAmount(Double amount) { this.amount = amount; }
    }
}
