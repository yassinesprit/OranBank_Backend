package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransfertRepository extends JpaRepository<Transfert,Integer> {
    List<Transfert> findByExpediteurClientId(int clientId);
    List<Transfert> findByExpediteurAlias(String alias);
}
