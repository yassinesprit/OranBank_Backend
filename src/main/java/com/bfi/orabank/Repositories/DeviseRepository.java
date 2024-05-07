package com.bfi.orabank.Repositories;

import com.bfi.orabank.Entities.Devise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviseRepository extends JpaRepository<Devise,Integer> {
}
