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
public class CompteBancaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String rib;
    String numeroCompte;
    String libelle;
    float solde;
    Date dateDeCreation;
    Boolean statut;

    @OneToOne
    Alias alias;

    @ManyToOne
    Client client;
}
