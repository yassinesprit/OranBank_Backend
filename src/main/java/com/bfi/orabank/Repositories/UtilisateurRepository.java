package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
    Utilisateur findByUsername(String username);
}
