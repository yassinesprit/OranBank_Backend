package com.bfi.orabank.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandePaiement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    float montant;
    Date dateCreation;
    Date dateModif;
    @Enumerated(EnumType.STRING)
    Etat etatDemande;

    @OneToMany(mappedBy = "demandePaiement")
    @JsonIgnore
    List<Notification> notifications;

    @ManyToOne
    Alias destinataire;
    @ManyToOne
    Alias expediteur;
}
