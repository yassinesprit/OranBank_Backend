package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.TransfertDto;
import com.bfi.orabank.Entities.*;
import com.bfi.orabank.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public Transfert createTransfert(TransfertDto transfertDto) throws Exception {
        float FraisTransfert = 5;
        Transfert transfert = new Transfert();
        log.info("///" + transfertDto.getReferenceBanque() + "/////" + transfertDto.getIdDevise());
        log.info("Received TransfertDto: {}", transfertDto.toString());


        if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParAlias) && aliasRepository.findByAlias(transfertDto.getDestinataireAlias()) == null) {
            throw new Exception("Alias est null");
        }
        if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParIBAN) && transfertDto.getReferenceBanque()==null) {
            throw new Exception("Iban est null");
        }
        if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParAutreCompte) && transfertDto.getNomInstitutFin()==null) {
            throw new Exception("Iban est null");
        }
        if (deviseRepository.findById(transfertDto.getIdDevise()).orElse(null) == null) {
            throw new Exception("devise est null");
        }
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

        } else if (transfertDto.getTypeTransfert().equals(TypeTransfert.ParIBAN)) {
            transfert.setReferenceBanque(transfertDto.getReferenceBanque());
            transfert.setPaysBanque(transfertDto.getPaysBanque());
            transfert.setNomBanque(transfertDto.getNomBanque());
            transfert.setNomInstitutFin(transfertDto.getNomInstitutFin());

        } else {
            transfert.setReferenceBanque(null);
            transfert.setPaysBanque(transfertDto.getPaysBanque());
            transfert.setNomBanque(null);
            transfert.setNomInstitutFin(transfertDto.getNomInstitutFin());
        }
        return transfertRepository.save(transfert);
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



