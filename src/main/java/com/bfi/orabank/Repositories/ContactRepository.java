package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
