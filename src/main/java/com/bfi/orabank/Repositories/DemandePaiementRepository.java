package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.DemandePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandePaiementRepository extends JpaRepository<DemandePaiement,Integer> {
    List <DemandePaiement> findByExpediteurAlias(String s);
    List <DemandePaiement> findByDestinataireAlias(String s);
}
