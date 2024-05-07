package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire,Integer> {
    List<CompteBancaire> findByClientId(int clientId);
    CompteBancaire findByAliasAlias(String s);

}
