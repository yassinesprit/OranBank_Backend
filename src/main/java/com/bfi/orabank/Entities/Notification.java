package com.bfi.orabank.Entities;

import java.io.Serializable;
import java.util.Date;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String titre;
    String message;
    @Enumerated(EnumType.STRING)
    TypeNotification type;
    @Enumerated(EnumType.STRING)
    StatusNotification status;
    Boolean lu;
    Date date;
    String expediteurAlias;
    String destinataireAlias;
    @ManyToOne
    DemandePaiement demandePaiement ;

    @ManyToOne
    Transfert transfert ;
}
