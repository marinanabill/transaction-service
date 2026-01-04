package com.wallet.transaction_service.repository;

import com.wallet.transaction_service.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByFromUserOrToUser(String fromUser, String toUser);
}
