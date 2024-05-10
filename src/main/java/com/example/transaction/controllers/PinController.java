package com.example.transaction.controllers;

import com.example.transaction.models.dtos.AccountPinDto;
import com.example.transaction.models.dtos.BalanceStatusDto;
import com.example.transaction.services.PinService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pin")
public class PinController {

    private final PinService pinService;

    public PinController(PinService pinService) {
        this.pinService = pinService;
    }

    @GetMapping("/check")
    public BalanceStatusDto checkPin(@RequestBody AccountPinDto accountPinDto) {
        return pinService.checkPin(accountPinDto);
    }
}
