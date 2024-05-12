package com.example.transaction.controllers;

import com.example.transaction.models.dtos.AccountNumberAmountDto;
import com.example.transaction.models.dtos.BalanceDto;
import com.example.transaction.models.dtos.StatusAccountNumberDto;
import com.example.transaction.services.CardAccountService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/withdraw")
public class WithdrawalController {
    private final CardAccountService cardAccountService;

    public WithdrawalController(CardAccountService cardAccountService) {
        this.cardAccountService = cardAccountService;
    }

    @PutMapping("/")
    public BalanceDto checkAndBlockBalance (@RequestBody AccountNumberAmountDto accountNumberAmountDto) {
        return cardAccountService.checkAndBlockBalance(accountNumberAmountDto);
    }

    @PutMapping("/close")
    public void closeWithdrawal (@RequestBody StatusAccountNumberDto statusAccountNumberDto) {
        cardAccountService.closeWithdrawal(statusAccountNumberDto);
    }

}
