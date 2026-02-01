package com.erica.fintech.MobileMoneyPlatform.controller.api;

import com.erica.fintech.MobileMoneyPlatform.model.Transaction;
import com.erica.fintech.MobileMoneyPlatform.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionApiController {

    private final TransactionService transactionService;

    public TransactionApiController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getMyTransactions(
            @AuthenticationPrincipal UserDetails userDetails) {

        return transactionService.getUserTransactionsByPhone(
                userDetails.getUsername());
    }
}
