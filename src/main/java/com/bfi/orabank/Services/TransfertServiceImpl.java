package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.TransfertDto;
import com.bfi.orabank.Entities.Transfert;
import com.bfi.orabank.Repositories.AliasRepository;
import com.bfi.orabank.Repositories.DeviseRepository;
import com.bfi.orabank.Repositories.TransfertRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransfertServiceImpl implements IServiceTransfert{

    TransfertRepository transfertRepository;
    AliasRepository aliasRepository;
    DeviseRepository deviseRepository;


    @Override
    public Transfert createTransfert(TransfertDto transfertDto) throws Exception {
        Transfert transfert=new Transfert();
        log.info("///"+transfertDto.getIdAlias()+"/////"+transfertDto.getIdDevise());
        if (aliasRepository.findById(transfertDto.getIdAlias()).orElse(null)==null)
        {throw new Exception("Alias est null");}
        if (deviseRepository.findById(transfertDto.getIdDevise()).orElse(null)==null)
        {throw new Exception("Client est null");}
        transfert.setDevise(deviseRepository.findById(transfertDto.getIdDevise()).orElse(null));
        transfert.setAlias(aliasRepository.findById(transfertDto.getIdAlias()).orElse(null));
        transfert.setTypeTransfert(transfertDto.getTypeTransfert());
        transfert.setDate(new Date());
        transfert.setDescription(transfertDto.getDescription());
        transfert.setMontant(transfertDto.getMontant());
        transfert.setFraisDeTransaction(transfertDto.getFraisDeTransaction());
        transfert.setReference(transfertDto.getReference());
        return transfertRepository.save(transfert);
    }

    @Override
    public List<Transfert> retrieveTransfertByClient(int clientId) {
        return transfertRepository.findByAliasClientId(clientId);
    }

    @Override
    public Transfert retrieveTransfertById(int id) {
        return transfertRepository.findById(id).orElse(null);
    }
}



