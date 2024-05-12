package com.example.transaction.services.impl;

import com.example.transaction.exceptions.NoAccountFound;
import com.example.transaction.exceptions.NotEnoughMoney;
import com.example.transaction.models.Balance;
import com.example.transaction.models.CardAccount;
import com.example.transaction.models.dtos.AccountNumberAmountDto;
import com.example.transaction.models.dtos.BalanceDto;
import com.example.transaction.models.dtos.StatusAccountNumberDto;
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

    @Override
    public void closeWithdrawal(StatusAccountNumberDto statusAccountNumberDto) {
        CardAccount cardAccount = cardAccountRepo.findByAccountNumber(statusAccountNumberDto.accountNumber());
        Balance balance = cardAccount.getBalance();

        if (statusAccountNumberDto.status().equalsIgnoreCase("ok"))
            balance.setBlockedBalance(balance.getBlockedBalance() - statusAccountNumberDto.amount());

        if (statusAccountNumberDto.status().equalsIgnoreCase("failed")) {
            balance.setBalance(balance.getBalance() + statusAccountNumberDto.amount());
            balance.setBlockedBalance(balance.getBlockedBalance() - statusAccountNumberDto.amount());
        }

        balanceService.saveBalance(balance);
    }
}
