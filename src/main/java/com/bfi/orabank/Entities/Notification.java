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
    String message;
    Boolean is_read;
    Date date;

    @ManyToOne
    DemandePaiement demandePaiement ;

    @ManyToOne
    Transfert transfert ;
}
