package com.bfi.orabank.Mappers;

import com.bfi.orabank.DTO.UserResponse;
import com.bfi.orabank.Entities.Utilisateur;

public class UserMapper {
    public static UserResponse mapToUserResponse(Utilisateur user) {
        UserResponse userResponse= UserResponse.builder()
                .id(user.getId())
                .role(user.getRole())
                .statut(user.getStatut())
                .email(user.getEmail())
                .nom(user.getNom())
                .telephone(user.getTelephone())
                .prenom(user.getPrenom())
                .username(user.getUsername())
                .langue(user.getLangue())
                .build();

        return userResponse;
    }
}
