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
@Table(name = "entryTriesCounts")
public class EntryTriesCount {
    @Id
    @GeneratedValue
    Long id;
    int triesCount;
    int maxCount;
    @OneToOne
    @JoinColumn(name = "card_account_id")
    CardAccount cardAccount;
}
