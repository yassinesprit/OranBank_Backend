package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.UserResponse;
import com.bfi.orabank.Entities.Client;
import com.bfi.orabank.Entities.Utilisateur;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IServiceUtilisateur {

    List<UserResponse> retrieveUsers();

    UserResponse  retrieveUserById(int idUser);

    List<UserResponse> getUserbyRole(String role);

    Utilisateur loadUserByUsername(String s);

    String getUserRoleByUsername(String username);
    Client getClientByUser(String username);


}
