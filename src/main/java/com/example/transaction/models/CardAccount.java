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
@Table(name = "cardAccounts")
public class CardAccount {
    @Id
    @GeneratedValue
    Long id;
    String accountNumber;
    int pin;
    boolean active;
    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;
    @OneToOne
    @JoinColumn(name = "balance_id")
    Balance balance;
    @OneToOne
    @JoinColumn(name = "entryTriesCount_id")
    EntryTriesCount entryTriesCount;
}
