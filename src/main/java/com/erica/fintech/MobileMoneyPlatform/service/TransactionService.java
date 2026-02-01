package com.erica.fintech.MobileMoneyPlatform.service;

import com.erica.fintech.MobileMoneyPlatform.model.Transaction;
import com.erica.fintech.MobileMoneyPlatform.model.User;
import com.erica.fintech.MobileMoneyPlatform.repository.TransactionRepository;
import com.erica.fintech.MobileMoneyPlatform.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository txRepo;
    private final UserRepository userRepo;

    public TransactionService(TransactionRepository txRepo, UserRepository userRepo) {
        this.txRepo = txRepo;
        this.userRepo = userRepo;
    }

    public List<Transaction> getUserTransactionsByPhone(String phone) {
        User user = userRepo.findByPhone(phone)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return txRepo.findByOwnerIdOrderByTimestampDesc(user.getId());
    }

}

