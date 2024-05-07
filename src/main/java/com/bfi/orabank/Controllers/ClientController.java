package com.bfi.orabank.Controllers;

import com.bfi.orabank.Entities.Client;
import com.bfi.orabank.Services.IServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {
    IServiceClient serviceClient;

    @PostMapping
    ResponseEntity<Object> createClient(@RequestBody Client client){
        Assert.notNull(client,"Client is null");
        return ResponseEntity.accepted().body(serviceClient.createClient(client));
    }

    @GetMapping
    ResponseEntity<Object> retrieveAllClient(){
        return ResponseEntity.ok().body(serviceClient.retrieveAllClient());
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> retrieveById(@PathVariable int id){
        Assert.notNull(id,"parametre null");
        return ResponseEntity.ok().body(serviceClient.retrieveById(id));
    }

    @GetMapping("/alias/{alias}")
    ResponseEntity<Object> retrieveByAlias(@PathVariable String alias){
        Assert.notNull(alias,"parametre null");
        return ResponseEntity.ok().body(serviceClient.retrieveByAlias(alias));
    }



    @GetMapping("/Comptebancaire/{id}")
    ResponseEntity<Object> retrieveByCompteBancaire(@PathVariable int id){
        Assert.notNull(id,"parametre null");
        return ResponseEntity.ok().body(serviceClient.retrieveByCompteBancaire(id));
    }

    @GetMapping("/username/{s}")
    ResponseEntity<Object> retrieveByUsername(@PathVariable String s) {
        Assert.notNull(s,"username est null");
        return ResponseEntity.ok().body(serviceClient.retrieveByUsername(s));
    }
}
