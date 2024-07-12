package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.TransfertDto;
import com.bfi.orabank.Entities.*;
import com.bfi.orabank.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransfertServiceImpl implements IServiceTransfert {

    TransfertRepository transfertRepository;
    AliasRepository aliasRepository;
    DeviseRepository deviseRepository;
    CompteBancaireRepository compteBancaireRepository;
    NotificationRepository notificationRepository;
    private SimpMessagingTemplate template;


    @Override
    public Transfert createTransfert(TransfertDto transfertDto) throws Exception {
        float FraisTransfert = 5;
        String destinataire="";
        Transfert transfert = new Transfert();
        log.info("///" + transfertDto.getReferenceBanque() + "/////" + transfertDto.getIdDevise());
        log.info("1/" + transfertDto.getDestinataireAlias() + "2//" );
        log.info("Received TransfertDto: {}", transfertDto.toString());
        CompteBancaire compteBancaireDestinataire=compteBancaireRepository.findByAliasAlias(transfertDto.getDestinataireAlias());
        CompteBancaire compteBancaireExpediteur=compteBancaireRepository.findByAliasAlias(transfertDto.getExpediteurAlias());

        if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParAlias) && aliasRepository.findByAlias(transfertDto.getDestinataireAlias()) == null) {
            throw new Exception("Alias est null");
        }
        if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParIBAN) && transfertDto.getReferenceBanque() == null) {
            throw new Exception("Iban est null");
        }
        if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParAutreCompte) && transfertDto.getNomInstitutFin() == null) {
            throw new Exception("Iban est null");
        }
        if (deviseRepository.findById(transfertDto.getIdDevise()).orElse(null) == null) {
            throw new Exception("devise est null");
        }
        if (compteBancaireRepository.findByAliasAlias(transfertDto.getExpediteurAlias()).getSolde() < transfertDto.getMontant() ) {
            throw new Exception("votre solde est insuffisant");
        }

        compteBancaireDestinataire.setSolde(compteBancaireDestinataire.getSolde()+transfertDto.getMontant());
        compteBancaireExpediteur.setSolde(compteBancaireExpediteur.getSolde()-transfertDto.getMontant());
        compteBancaireRepository.save(compteBancaireDestinataire);
        compteBancaireRepository.save(compteBancaireExpediteur);
        transfert.setDevise(deviseRepository.findById(transfertDto.getIdDevise()).orElse(null));
        transfert.setExpediteur(aliasRepository.findByAlias(transfertDto.getExpediteurAlias()));
        transfert.setTypeTransfert(transfertDto.getTypeTransfert());
        transfert.setDate(new Date());
        transfert.setDescription(transfertDto.getDescription());
        transfert.setMontant(transfertDto.getMontant());
        transfert.setFraisDeTransaction(FraisTransfert);
        transfert.setReference(transfertDto.getReference());
        transfert.setStatusTransfert(transfertDto.getStatusTransfert());
        if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParAlias)) {
            transfert.setDestinataire(aliasRepository.findByAlias(transfertDto.getDestinataireAlias()));
            transfert.setReferenceBanque(null);
            transfert.setPaysBanque(null);
            transfert.setNomBanque(null);
            transfert.setNomInstitutFin(transfertDto.getNomInstitutFin());
            destinataire=aliasRepository.findByAlias(transfertDto.getDestinataireAlias()).getCompteBancaire().getNumeroCompte();
        } else if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParIBAN)) {
            transfert.setReferenceBanque(transfertDto.getReferenceBanque());
            transfert.setPaysBanque(transfertDto.getPaysBanque());
            transfert.setNomBanque(transfertDto.getNomBanque());
            transfert.setNomInstitutFin(transfertDto.getNomInstitutFin());
            destinataire=transfertDto.getReferenceBanque();
        } else {
            transfert.setReferenceBanque(null);
            transfert.setPaysBanque(transfertDto.getPaysBanque());
            transfert.setNomBanque(null);
            transfert.setNomInstitutFin(transfertDto.getNomInstitutFin());
            destinataire=transfertDto.getNomInstitutFin();

        }
        // Enregistrement du transfert
        Transfert savedTransfert = transfertRepository.save(transfert);
        String expediteurAlias = transfert.getExpediteur().getAlias();
        String destinataireAlias="";
        if (transfert.getDestinataire()!=null){
        destinataireAlias = transfert.getDestinataire().getAlias();}
        // Création et envoi de la notification
        Notification notification_expediteur = new Notification();
        notification_expediteur.setTitre("Un nouveau transfert a été créé");
        notification_expediteur.setMessage("Un nouveau transfert de " + savedTransfert.getMontant() + " au compte " + transfertDto.getExpediteurAlias()+" a été créé avec succés");
        notification_expediteur.setDate(new Date());
        notification_expediteur.setTransfert(savedTransfert);
        notification_expediteur.setLu(false);
        notification_expediteur.setExpediteurAlias(expediteurAlias);
        notification_expediteur.setDestinataireAlias(destinataireAlias);
        notification_expediteur.setStatus(StatusNotification.nonLu);
        notification_expediteur.setType(TypeNotification.success);
        Notification savedNotification_expediteur = notificationRepository.save(notification_expediteur);


        Notification notification_destinataire = new Notification();
        notification_destinataire.setTitre("Un nouveau transfert a été créé");
        notification_destinataire.setMessage("Un nouveau transfert de " + savedTransfert.getMontant() + " de la part du compte " + destinataire+" a été créé avec succés");
        notification_destinataire.setDate(new Date());
        notification_destinataire.setTransfert(savedTransfert);
        notification_destinataire.setLu(false);
        notification_destinataire.setStatus(StatusNotification.nonLu);
        notification_destinataire.setExpediteurAlias(expediteurAlias);
        notification_destinataire.setDestinataireAlias(destinataireAlias);
        notification_expediteur.setType(TypeNotification.info);
        Notification savedNotification_destinataire = notificationRepository.save(notification_destinataire);

        // Envoi de la notification à l'expéditeur et au destinataire
        template.convertAndSendToUser(expediteurAlias, "/queue/notifications", savedNotification_expediteur);
        log.info("Envoi de la notification à l'expéditeur : " + expediteurAlias);
        template.convertAndSendToUser(destinataireAlias, "/queue/notifications", savedNotification_destinataire);
        log.info("Envoi de la notification au destinataire : " + destinataireAlias);
        return savedTransfert;
    }

    @Override
    public List<Transfert> retrieveTransfertByClient(int clientId) {
        return transfertRepository.findByExpediteurClientId(clientId);
    }

    @Override
    public Transfert retrieveTransfertById(int id) {
        return transfertRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transfert> retrieveTransfertByAlias(String alias) throws Exception {
        return transfertRepository.findByExpediteurAlias(alias);
    }


}



