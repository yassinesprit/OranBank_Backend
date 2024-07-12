package com.bfi.orabank.Controllers;

import com.bfi.orabank.DTO.DemandeDto;
import com.bfi.orabank.Entities.DemandePaiement;
import com.bfi.orabank.Services.IDemandePaiementService;
import io.jsonwebtoken.lang.Assert;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demandes")
@AllArgsConstructor
public class DemandePaiementController {
    private IDemandePaiementService demandePaiementService;

    @GetMapping
    public List<DemandePaiement> getAllDemandes() {
        return demandePaiementService.getAllDemandes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandePaiement> getDemandeById(@PathVariable int id) throws Exception {
        DemandePaiement demandePaiement = demandePaiementService.getDemandeById(id);
        return ResponseEntity.ok(demandePaiement);
    }

    @PostMapping
    public DemandePaiement createDemande(@RequestBody DemandeDto demandePaiement) throws Exception {
        return demandePaiementService.createDemande(demandePaiement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandePaiement> updateDemande(@PathVariable int id, @RequestBody DemandePaiement demandeDetails) throws Exception {
        DemandePaiement updatedDemande = demandePaiementService.updateDemande(id, demandeDetails);
        return ResponseEntity.ok(updatedDemande);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable int id) {
        demandePaiementService.deleteDemande(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/destinataire/{s}")
    List<DemandePaiement> getAllDemandeByAliasDestinataire(@PathVariable String s){
        Assert.notNull(s,"alias null");
        return demandePaiementService.getAllDemandeByAliasDestinataire(s);
    }
    @GetMapping("/expediteur/{s}")
    List<DemandePaiement> getAllDemandeByAliasExpediteur(@PathVariable String s){
        Assert.notNull(s,"alias null");
        return demandePaiementService.getAllDemandeByAliasExpediteur(s);
    }
}
