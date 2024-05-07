package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.CompteBancaire;

import java.util.List;

public interface IServiceCompteBancaire {
    CompteBancaire createCompteBancaire(CompteBancaire compteBancaire);
    List<CompteBancaire> findAllCompteBancaireByClient(int clientId);
    CompteBancaire findCompteById(int compteId);
    CompteBancaire findComptebyAlias(String s);
    List<CompteBancaire> findCompteBancaireByUsername(String s);

}
