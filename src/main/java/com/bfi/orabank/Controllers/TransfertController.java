package com.bfi.orabank.Controllers;


import com.bfi.orabank.DTO.TransfertDto;
import com.bfi.orabank.Entities.Transfert;
import com.bfi.orabank.Services.IServiceTransfert;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transfert")
public class TransfertController {

    IServiceTransfert serviceTransfert ;

    @PostMapping
    ResponseEntity<Object> createTransfert(@RequestBody TransfertDto transfert) throws Exception {
        Assert.notNull(transfert,"objet est null");
        return ResponseEntity.accepted().body(serviceTransfert.createTransfert(transfert));
    }


    @GetMapping("/client/{clientId}")
    ResponseEntity<Object> retrieveTransfertByClient(@PathVariable int clientId){
        Assert.notNull(clientId,"client est null");
        return ResponseEntity.ok(serviceTransfert.retrieveTransfertByClient(clientId));
    }


    @GetMapping("/{id}")
    ResponseEntity<Object> retrieveTransfertById(@PathVariable int id){
        Assert.notNull(id,"objet est null");
        return ResponseEntity.ok(serviceTransfert.retrieveTransfertById(id));
    }

    @GetMapping("/alias/{alias}")
    ResponseEntity<Object> retrieveTransfertByAlias(@PathVariable String alias) throws Exception {
        Assert.notNull(alias,"alias est null");
        return ResponseEntity.ok(serviceTransfert.retrieveTransfertByAlias(alias));
    }
}
