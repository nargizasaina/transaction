package com.example.transaction.services;

import com.example.transaction.models.dtos.AccountPinDto;
import com.example.transaction.models.dtos.BalanceStatusDto;

public interface PinService {
    BalanceStatusDto checkPin(AccountPinDto accountPinDto);
}
