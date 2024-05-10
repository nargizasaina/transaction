package com.example.transaction.models.dtos;

public record AccountPinDto(
        String accountNumber,
        int pin
) {
}
