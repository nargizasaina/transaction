package com.example.transaction.models.dtos;

public record StatusAccountNumberDto(
        String status,
        String accountNumber,
        double amount
) {
}
