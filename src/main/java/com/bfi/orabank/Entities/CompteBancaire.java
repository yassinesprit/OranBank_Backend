package com.bfi.orabank.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    String Iban;


    @OneToOne(mappedBy = "compteBancaire")
    Alias alias;

    @ManyToOne
    Client client;

    @OneToMany(mappedBy = "compteBancaire")
    List<Contact> contact;

}
