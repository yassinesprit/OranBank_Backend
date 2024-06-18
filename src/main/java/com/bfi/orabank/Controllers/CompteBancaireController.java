package com.bfi.orabank.Controllers;

import com.bfi.orabank.Entities.CompteBancaire;
import com.bfi.orabank.Services.IServiceCompteBancaire;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comptebancaire")
public class CompteBancaireController {

    IServiceCompteBancaire serviceCompteBancaire;


    @PostMapping
    ResponseEntity<Object> createCompteBancaire(@RequestBody CompteBancaire compteBancaire) {
        Assert.notNull(compteBancaire, "objet est null");
        return ResponseEntity.ok(serviceCompteBancaire.createCompteBancaire(compteBancaire));
    }


    @GetMapping("/client/{clientId}")
    ResponseEntity<Object> findAllCompteBancaireByClient(@PathVariable int clientId) {
        Assert.notNull(clientId, "client est null");
        return ResponseEntity.ok(serviceCompteBancaire.findAllCompteBancaireByClient(clientId));
    }

    @GetMapping("/{compteId}")
    ResponseEntity<Object> findCompteById(@PathVariable int compteId) {
        Assert.notNull(compteId, "id est null");
        return ResponseEntity.ok(serviceCompteBancaire.findCompteById(compteId));
    }

    @GetMapping("/alias/{s}")
    ResponseEntity<Object> findComptebyAlias(@PathVariable String s) {
        Assert.notNull(s, "alias est null");
        return ResponseEntity.ok(serviceCompteBancaire.findComptebyAlias(s));
    }

    @GetMapping("/username/{s}")
    ResponseEntity<Object> findComptebyUsername(@PathVariable String s) {
        Assert.notNull(s, "username est null");
        return ResponseEntity.ok(serviceCompteBancaire.findCompteBancaireByUsername(s));
    }

    @GetMapping("/free/username/{s}")
    ResponseEntity<Object> findFreeCompteBancaireByUsername(@PathVariable String s) {
        Assert.notNull(s, "username est null");
        return ResponseEntity.ok(serviceCompteBancaire.findFreeCompteBancaireByUsername(s));
    }

    }
