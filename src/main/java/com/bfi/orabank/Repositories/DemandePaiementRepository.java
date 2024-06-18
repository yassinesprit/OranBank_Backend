package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.DemandePaiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandePaiementRepository extends JpaRepository<DemandePaiement,Integer> {
}
