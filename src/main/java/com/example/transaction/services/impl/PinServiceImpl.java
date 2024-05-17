package com.example.transaction.services.impl;

import com.example.transaction.exceptions.ClientNotActive;
import com.example.transaction.exceptions.IncorrectPin;
import com.example.transaction.models.CardAccount;
import com.example.transaction.models.dtos.AccountPinDto;
import com.example.transaction.models.dtos.BalanceStatusDto;
import com.example.transaction.services.CardAccountService;
import com.example.transaction.services.PinService;
import org.springframework.stereotype.Service;

@Service
public class PinServiceImpl implements PinService {
    private final CardAccountService cardAccountService;

    public PinServiceImpl(CardAccountService cardAccountService) {
        this.cardAccountService = cardAccountService;
    }

    @Override
    public BalanceStatusDto checkPin(AccountPinDto accountPinDto) {
        CardAccount cardAccount = cardAccountService.findByAccountNumber(accountPinDto.accountNumber());

        if (!cardAccount.isActive())
            throw new ClientNotActive();

        if (cardAccount.getPin() != accountPinDto.pin())
            throw new IncorrectPin();

        BalanceStatusDto balanceStatusDto = new BalanceStatusDto(cardAccount.getBalance().getBalance(), cardAccount.isActive());
        return balanceStatusDto;
    }
}
