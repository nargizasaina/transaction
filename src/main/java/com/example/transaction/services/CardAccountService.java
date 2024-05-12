package com.example.transaction.services;

import com.example.transaction.models.CardAccount;
import com.example.transaction.models.dtos.AccountNumberAmountDto;
import com.example.transaction.models.dtos.BalanceDto;
import com.example.transaction.models.dtos.StatusAccountNumberDto;

public interface CardAccountService {
    CardAccount findByAccountNumber(String accountNumber);

    BalanceDto checkAndBlockBalance(AccountNumberAmountDto accountNumberAmountDto);


    void closeWithdrawal(StatusAccountNumberDto statusAccountNumberDto);
}
