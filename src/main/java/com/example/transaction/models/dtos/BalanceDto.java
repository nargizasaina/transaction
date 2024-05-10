package com.example.transaction.models.dtos;

public record BalanceDto(
        double balance,
        double blockedBalance
) {
}
