package com.example.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.*;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document("comptes")
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @Id
    private String id;
    private Double solde;
    private String rib;
}
