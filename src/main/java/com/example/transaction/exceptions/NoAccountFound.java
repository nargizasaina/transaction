package com.example.transaction.exceptions;

public class NoAccountFound extends RuntimeException{
    public NoAccountFound(String message) {
        super("Account not found! accountNumber = " + message);
    }
}
