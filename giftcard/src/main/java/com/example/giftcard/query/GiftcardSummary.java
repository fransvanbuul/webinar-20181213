package com.example.giftcard.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GiftcardSummary {
    @Id
    UUID id;

    int remainingValue;
    int initialValue;

}
