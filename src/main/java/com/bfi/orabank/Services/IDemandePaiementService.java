package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.DemandeDto;
import com.bfi.orabank.Entities.DemandePaiement;

import java.util.List;

public interface IDemandePaiementService {
    List<DemandePaiement> getAllDemandes();
    DemandePaiement getDemandeById(int id) throws Exception;
    DemandePaiement createDemande(DemandeDto demandePaiement) throws Exception;
    DemandePaiement updateDemande(int id, DemandePaiement demandePaiement) throws Exception;
    void deleteDemande(int id);
    List<DemandePaiement> getAllDemandeByAliasDestinataire(String s);
    List<DemandePaiement> getAllDemandeByAliasExpediteur(String s);
}
