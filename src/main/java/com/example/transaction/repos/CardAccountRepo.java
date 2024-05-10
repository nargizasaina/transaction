package com.example.transaction.repos;

import com.example.transaction.models.CardAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardAccountRepo extends JpaRepository<CardAccount, Long> {
    CardAccount findByAccountNumber(String accountNumber);
}
