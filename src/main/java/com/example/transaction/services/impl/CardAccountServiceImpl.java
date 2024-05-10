package com.example.transaction.services.impl;

import com.example.transaction.exceptions.NoAccountFound;
import com.example.transaction.exceptions.NotEnoughMoney;
import com.example.transaction.models.Balance;
import com.example.transaction.models.CardAccount;
import com.example.transaction.models.dtos.AccountNumberAmountDto;
import com.example.transaction.models.dtos.BalanceDto;
import com.example.transaction.repos.CardAccountRepo;
import com.example.transaction.services.BalanceService;
import com.example.transaction.services.CardAccountService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CardAccountServiceImpl implements CardAccountService {
    private final CardAccountRepo cardAccountRepo;
    private final BalanceService balanceService;

    public CardAccountServiceImpl(CardAccountRepo cardAccountRepo, BalanceService balanceService) {
        this.cardAccountRepo = cardAccountRepo;
        this.balanceService = balanceService;
    }

    @Override
    public CardAccount findByAccountNumber(String accountNumber) {
        CardAccount cardAccount = cardAccountRepo.findByAccountNumber(accountNumber);
        if (cardAccount == null)
            throw new NoAccountFound(accountNumber);


        return cardAccount;
    }

    @Transactional
    @Override
    public BalanceDto checkAndBlockBalance(AccountNumberAmountDto accountNumberAmountDto) {
        CardAccount cardAccount = cardAccountRepo.findByAccountNumber(accountNumberAmountDto.accountNumber());

        if (cardAccount.getBalance().getBalance() <= accountNumberAmountDto.amount())
            throw new NotEnoughMoney();

        Balance balance = cardAccount.getBalance();
        balance.setBalance(balance.getBalance() - accountNumberAmountDto.amount());
        balance.setBlockedBalance(balance.getBlockedBalance() + accountNumberAmountDto.amount());
        balanceService.saveBalance(balance);

        return new BalanceDto(balance.getBalance(), balance.getBlockedBalance());
    }
}
