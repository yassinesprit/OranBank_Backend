package com.bfi.orabank.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String reference;
    String description;
    float fraisDeTransaction;
    float montant;
    Date date;
    @Enumerated(EnumType.STRING)
    TypeTransfert typeTransfert;

    @ManyToOne
    Devise devise;

    @ManyToOne
    Alias alias;
}
