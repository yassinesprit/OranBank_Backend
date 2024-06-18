package com.bfi.orabank.Controllers;


import com.bfi.orabank.DTO.UserResponse;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.ExceptionHandler.NotFoundException;
import com.bfi.orabank.Services.IServiceUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UtilisateurController {
    IServiceUtilisateur iUserService;



    @GetMapping
    public ResponseEntity<Object> retrieveUsers() {
        List<UserResponse> responseList=iUserService.retrieveUsers();
        if(responseList==null){
            throw new NotFoundException("Resource not found");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseList) ;
    }



    @GetMapping("/{idUser}")
    public ResponseEntity<Object> retrieveUserById(@PathVariable int idUser) {

        Assert.notNull(idUser,"id User is null");

        UserResponse userResponse = iUserService.retrieveUserById(idUser);

        if (userResponse==null){
            throw new NotFoundException("USER NOT FOUND WITH ID" + idUser);
        }
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(userResponse);
    }




    @GetMapping("/username/{s}")
    public ResponseEntity<Object> loadUserByUsername(@PathVariable String s){

        Assert.notNull(s,"username is null");
        return ResponseEntity.ok(iUserService.loadUserByUsername(s));

    }
    /*@GetMapping("/username1/{s}")
    public Utilisateur loadUserByUsername1(String s){

        Assert.notNull(s,"username is null");
        return iUserService.loadUserByUsername1(s);
    }*/
}
