package com.wallet.transaction_service.service;

import com.wallet.transaction_service.model.Transaction;
import com.wallet.transaction_service.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction transfer(String fromUser, String toUser, double amount) {
        Transaction transaction = new Transaction();
        transaction.setFromUser(fromUser);
        transaction.setToUser(toUser);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getUserTransactions(String username) {
        return transactionRepository.findByFromUserOrToUser(username, username);
    }
}
