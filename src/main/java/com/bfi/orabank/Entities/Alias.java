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
public class Alias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String alias;
    String description;
    Date dateDeCreation;
    Boolean statut;
    String telephone;
    @Enumerated(EnumType.STRING)
    TypeAlias type;

    @ManyToOne
    @JsonIgnore
    Client client;

    @OneToOne
    @JsonIgnore
    CompteBancaire compteBancaire;

    @OneToMany(mappedBy = "destinataire")
    @JsonIgnore
    List<Transfert> transferts_dest;
    @OneToMany(mappedBy = "expediteur")
    @JsonIgnore
    List<Transfert> transferts_exp;


    @OneToMany(mappedBy = "destinataire")
    @JsonIgnore
    List<DemandePaiement> demandes_dest;
    @OneToMany(mappedBy = "expediteur")
    @JsonIgnore
    List<DemandePaiement> demandes_exp;
}
