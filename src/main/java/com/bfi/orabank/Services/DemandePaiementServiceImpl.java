package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.DemandeDto;
import com.bfi.orabank.Entities.*;
import com.bfi.orabank.Repositories.AliasRepository;
import com.bfi.orabank.Repositories.DemandePaiementRepository;
import com.bfi.orabank.Repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class DemandePaiementServiceImpl implements IDemandePaiementService {

    DemandePaiementRepository demandePaiementRepository;
    AliasRepository aliasRepository;
    NotificationRepository notificationRepository;
    private SimpMessagingTemplate template;


    @Override
    public List<DemandePaiement> getAllDemandes() {
        return demandePaiementRepository.findAll();
    }

    @Override
    public DemandePaiement getDemandeById(int id) throws Exception {
        Optional<DemandePaiement> demandePaiement = demandePaiementRepository.findById(id);
        if (demandePaiement.isPresent()) {
            return demandePaiement.get();
        } else {
            throw new Exception("DemandePaiement not found for id :: " + id);
        }
    }

    @Override
    public DemandePaiement createDemande(DemandeDto demandeDto) throws Exception {
        Alias aliasExp= aliasRepository.findByAlias(demandeDto.getAliasExp());
        Alias aliasDest= aliasRepository.findByAlias(demandeDto.getAliasDest());

        if (aliasDest==null){
            throw new Exception("Alias destinataire inexistant");
        }

        if (aliasExp==null){
            throw new Exception("Alias expidateur inexistant");
        }

        DemandePaiement demandePaiement = new DemandePaiement();
        demandePaiement.setEtatDemande(Etat.EnAttente);
        demandePaiement.setDateCreation(new Date());
        demandePaiement.setDescription(demandeDto.getDescription());
        demandePaiement.setExpediteur(aliasExp);
        demandePaiement.setDestinataire(aliasDest);
        demandePaiement.setMontant(demandeDto.getMontant());
        DemandePaiement savedDemande=demandePaiementRepository.save(demandePaiement);

        // Création et envoi de la notification
        Notification notification_expediteur = new Notification();
        notification_expediteur.setTitre("Une nouvelle demande de paiement a été créé");
        notification_expediteur.setMessage("Une nouvelle demande de paiement de " + demandePaiement.getMontant() + " à  " + demandeDto.getAliasDest()+" a été créé avec succés");
        notification_expediteur.setDate(new Date());
        notification_expediteur.setDemandePaiement(savedDemande);
        notification_expediteur.setLu(false);
        notification_expediteur.setExpediteurAlias(aliasExp.getAlias());
        notification_expediteur.setDestinataireAlias(aliasDest.getAlias());
        notification_expediteur.setStatus(StatusNotification.nonLu);
        notification_expediteur.setType(TypeNotification.success);
        Notification savedNotification_expediteur = notificationRepository.save(notification_expediteur);


        Notification notification_destinataire = new Notification();
        notification_destinataire.setTitre("Un nouveau transfert a été créé");
        notification_expediteur.setMessage("Une nouvelle demande de paiement de " + demandePaiement.getMontant() + " de la part de   " + demandeDto.getAliasDest()+" a été créé avec succés");
        notification_destinataire.setDate(new Date());
        notification_destinataire.setDemandePaiement(savedDemande);
        notification_destinataire.setLu(false);
        notification_destinataire.setStatus(StatusNotification.nonLu);
        notification_expediteur.setExpediteurAlias(aliasExp.getAlias());
        notification_expediteur.setDestinataireAlias(aliasDest.getAlias());
        notification_expediteur.setType(TypeNotification.info);
        Notification savedNotification_destinataire = notificationRepository.save(notification_destinataire);

        // Envoi de la notification à l'expéditeur et au destinataire
        template.convertAndSendToUser(aliasExp.getAlias(), "/queue/notifications", savedNotification_expediteur);
        log.info("Envoi de la notification à l'expéditeur : " + aliasExp.getAlias());
        template.convertAndSendToUser(aliasDest.getAlias(), "/queue/notifications", savedNotification_destinataire);
        log.info("Envoi de la notification au destinataire : " + aliasDest.getAlias());
        return savedDemande ;
    }

    @Override
    public DemandePaiement updateDemande(int id, DemandePaiement demandePaiement) throws Exception {
        DemandePaiement existingDemande = getDemandeById(id);
        existingDemande.setMontant(demandePaiement.getMontant());
        existingDemande.setDateCreation(demandePaiement.getDateCreation());
        existingDemande.setDateModif(demandePaiement.getDateModif());
        existingDemande.setEtatDemande(demandePaiement.getEtatDemande());
        existingDemande.setNotifications(demandePaiement.getNotifications());
        existingDemande.setDestinataire(demandePaiement.getDestinataire());
        existingDemande.setExpediteur(demandePaiement.getExpediteur());
        return demandePaiementRepository.save(existingDemande);
    }

    @Override
    public void deleteDemande(int id) {
        demandePaiementRepository.deleteById(id);
    }

    @Override
    public List<DemandePaiement> getAllDemandeByAliasDestinataire(String s) {
        return demandePaiementRepository.findByDestinataireAlias(s);
    }

    @Override
    public List<DemandePaiement> getAllDemandeByAliasExpediteur(String s) {
        return demandePaiementRepository.findByExpediteurAlias(s);
    }
}
