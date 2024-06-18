package com.bfi.orabank.Controllers;

import com.bfi.orabank.DTO.AliasRequest;
import com.bfi.orabank.Entities.Alias;
import com.bfi.orabank.Services.IServiceAlias;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/alias")
public class AliasController {

    IServiceAlias serviceAlias ;

    @PostMapping
    ResponseEntity<Object> createAlias(@RequestBody AliasRequest alias) throws Exception {
        Assert.notNull(alias,"Alias object est null");
        return ResponseEntity.accepted().body(serviceAlias.createAlias(alias));
    }

    @GetMapping
    ResponseEntity<Object> retrieveAliass (){
        return ResponseEntity.ok(serviceAlias.retrieveAliass());
    }


    @GetMapping("/{idAlias}")
    ResponseEntity<Object>  retrieveAliasById (@PathVariable int idAlias){
        Assert.notNull(idAlias,"id est null");
        return ResponseEntity.ok(serviceAlias.retrieveAliasById(idAlias));
    }


    @GetMapping("/client/{s}")
    ResponseEntity<Object> loadAliasByClient (@PathVariable int s){
        Assert.notNull(s,"client est null");
        return ResponseEntity.ok(serviceAlias.loadAliasByClient(s));
    }

    @GetMapping("/comptebancaire/{id}")
    ResponseEntity<Object> loadAliasByCompteBancaireId(@PathVariable int id) throws Exception {
        Assert.notNull(id,"parametre est null");
        return ResponseEntity.ok(serviceAlias.loadAliasByCompteBancaireId(id));
    }
}
