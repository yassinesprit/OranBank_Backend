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
    @Enumerated(EnumType.STRING)
    TypeAlias type;

    @ManyToOne
    @JsonIgnore
    Client client;

    @OneToOne(mappedBy = "alias")
    @JsonIgnore
    CompteBancaire compteBancaire;

    @OneToMany(mappedBy = "alias")
    @JsonIgnore
    List<Transfert> transferts;
}
