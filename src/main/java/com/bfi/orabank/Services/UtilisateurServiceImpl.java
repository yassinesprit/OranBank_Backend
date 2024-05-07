package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.UserResponse;
import com.bfi.orabank.Entities.Client;
import com.bfi.orabank.Entities.Role;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.Mappers.UserMapper;
import com.bfi.orabank.Repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements IServiceUtilisateur{

    UtilisateurRepository utilisateurRepository;

    @Override
    public List<UserResponse> retrieveUsers() {
        List<Utilisateur> users=utilisateurRepository.findAll();
        List<UserResponse> responseList = new ArrayList<>();
        for (Utilisateur u:users) {
            UserResponse userResponse= UserMapper.mapToUserResponse(u);
            responseList.add(userResponse);
        }
        return responseList ;

    }

    @Override
    public UserResponse retrieveUserById(int idUser) {
        Utilisateur optionalUser = utilisateurRepository.findById(idUser).orElse(null);

        UserResponse userResponse= UserMapper.mapToUserResponse(optionalUser);
        if (optionalUser==null){
            return null;
        }
        return userResponse;
    }


    @Override
    public List<UserResponse> getUserbyRole(String role) {
        List<UserResponse> responseList = new ArrayList<>();
        if (!Role.Utilisateur.equals(role) || !Role.Admin.equals(role)) {
            List<Utilisateur> result = utilisateurRepository.findAll()
                    .stream()
                    .filter(e -> e.getRole().toString() == role)
                    .collect(Collectors.toList());
            if (!result.isEmpty()) {
                for (Utilisateur u:result) {
                    UserResponse userResponse= UserMapper.mapToUserResponse(u);
                    responseList.add(userResponse);
                }
                return responseList;

            }
            return null;
        }
        return null;
    }

    @Override
    public Utilisateur loadUserByUsername(String s) {
        return utilisateurRepository.findByUsername(s);
    }

    @Override
    public String getUserRoleByUsername(String username) {
        Utilisateur user=loadUserByUsername(username);
        String role = user.getRole().toString();
        return role;
    }

    @Override
    public Client getClientByUser(String username) {
        Client client= this.loadUserByUsername(username).getClient();
        return client;
    }
}
