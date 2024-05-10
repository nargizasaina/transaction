package com.example.transaction.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "accountHistories")
public class AccountHistory {
    @Id
    @GeneratedValue
    Long id;
    double amount;
    Date addDate;
    @ManyToOne
    @JoinColumn(name = "card_account_id")
    CardAccount cardAccount;
}
