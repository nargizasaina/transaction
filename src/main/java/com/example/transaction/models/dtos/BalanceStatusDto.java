package com.example.transaction.models.dtos;

public record BalanceStatusDto(
        double balance,
        boolean active
) {
}
