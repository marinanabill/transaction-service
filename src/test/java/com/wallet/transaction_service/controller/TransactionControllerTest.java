package com.wallet.transaction_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.transaction_service.model.Transaction;
import com.wallet.transaction_service.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    @Test
    @WithMockUser(username = "marina", roles = {"USER"})
    void transfer_shouldReturnTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setFromUser("marina");
        transaction.setToUser("ahmed");
        transaction.setAmount(50.0);
        transaction.setTimestamp(LocalDateTime.now());

        when(transactionService.transfer(any(String.class), any(String.class), any(Double.class)))
                .thenReturn(transaction);

        TransferRequest request = new TransferRequest("ahmed", 50.0);

        mockMvc.perform(post("/transactions/transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fromUser").value("marina"))
                .andExpect(jsonPath("$.toUser").value("ahmed"))
                .andExpect(jsonPath("$.amount").value(50.0));
    }

    static class TransferRequest {
        public String toUser;
        public double amount;
        public TransferRequest(String toUser, double amount) {
            this.toUser = toUser;
            this.amount = amount;
        }
    }
}
