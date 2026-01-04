package com.wallet.transaction_service.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {
    private Long toUserId;
    private double amount;
}
