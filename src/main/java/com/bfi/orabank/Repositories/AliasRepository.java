package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Alias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AliasRepository extends JpaRepository<Alias,Integer> {
    Alias findByClientId (int idClient);
    Alias findByCompteBancaireId (int idCompte);
    Alias findByAlias(String s);
}
