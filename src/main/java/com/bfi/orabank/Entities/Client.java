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
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nom;
    String adresse;
    String telephone;
    String pays;
    Date dateDeFinDeContrat;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "client")
            @JsonIgnore
    List<Alias> aliases;

    @OneToMany(mappedBy = "client")
            @JsonIgnore
    List<CompteBancaire> compteBancaires;
}
