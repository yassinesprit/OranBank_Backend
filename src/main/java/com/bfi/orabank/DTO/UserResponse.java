package com.bfi.orabank.DTO;

import com.bfi.orabank.Entities.Role;
import lombok.Builder;

@Builder
public class UserResponse {
    int id;
    String username;
    String email;
    String nom;
    String prenom;
    String telephone;
    String langue;
    Boolean statut;
    Role role;
}
