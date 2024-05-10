package com.example.transaction.services.impl;

import com.example.transaction.models.Balance;
import com.example.transaction.repos.BalanceRepo;
import com.example.transaction.services.BalanceService;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final BalanceRepo balanceRepo;

    public BalanceServiceImpl(BalanceRepo balanceRepo) {
        this.balanceRepo = balanceRepo;
    }

    @Override
    public void saveBalance(Balance balance) {
        balanceRepo.save(balance);
    }
}
