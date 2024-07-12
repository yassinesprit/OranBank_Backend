package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    List<Contact> findByUtilisateurUsername(String s);
}
