package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
}
