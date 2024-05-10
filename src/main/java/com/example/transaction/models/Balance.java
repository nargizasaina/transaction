package com.example.transaction.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "balances")
public class Balance {
    @Id
    @GeneratedValue
    Long id;
    double balance;
    double blockedBalance;
    boolean active;
    @OneToOne
    CardAccount cardAccount;
}
