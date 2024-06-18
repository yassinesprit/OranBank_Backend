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
public class Transfert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String reference;
    String referenceBanque;
    String description;
    float fraisDeTransaction;
    float montant;
    Date date;
    String nomBanque;
    String PaysBanque;
    String nomInstitutFin;
    @Enumerated(EnumType.STRING)
    TypeTransfert typeTransfert;

    @Enumerated(EnumType.STRING)
    StatusTransfert statusTransfert;

    @Enumerated(EnumType.STRING)
    Etat etatTransfert;

    @ManyToOne
    Devise devise;

    @ManyToOne
    Alias destinataire;
    @ManyToOne
    Alias expediteur;

    @OneToMany(mappedBy = "transfert")
    @JsonIgnore
    List<Notification> notifications;
}
