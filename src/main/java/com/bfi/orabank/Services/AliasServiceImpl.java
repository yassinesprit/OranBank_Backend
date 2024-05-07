package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.AliasRequest;
import com.bfi.orabank.Entities.Alias;
import com.bfi.orabank.Entities.Client;
import com.bfi.orabank.Entities.CompteBancaire;
import com.bfi.orabank.Repositories.AliasRepository;
import com.bfi.orabank.Repositories.ClientRepository;
import com.bfi.orabank.Repositories.CompteBancaireRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class AliasServiceImpl implements IServiceAlias {

    AliasRepository aliasRepository;
    ClientRepository clientRepository;
    CompteBancaireRepository compteBancaireRepository;

    @Override
    public Alias createAlias(AliasRequest aliasRequest) throws Exception {
        Client client = clientRepository.findById(aliasRequest.getClientId())
                .orElse(null);
        if (aliasRepository.findByClientId(aliasRequest.getClientId()) == null) {
            if (client != null) {
                Alias alias = new Alias();
                alias.setClient(client);
                alias.setAlias(UUID.randomUUID().toString());
                alias.setDescription(aliasRequest.getDescription());
                alias.setDateDeCreation(new Date());
                alias.setStatut(true);
                alias.setType(aliasRequest.getType());
                return aliasRepository.save(alias);
            } else throw new Exception("Propriétaire est null");
        } else throw new Exception("client a deja un alias");

    }

    @Override
    public List<Alias> retrieveAliass() {
        return aliasRepository.findAll();
    }

    @Override
    public Alias retrieveAliasById(int idAlias) {
        return aliasRepository.findById(idAlias).orElse(null);
    }

    @Override
    public Alias loadAliasByClient(int s) {
        return aliasRepository.findByClientId(s);
    }

    @Override
    public Alias loadAliasByCompteBancaireId(int id) {
        CompteBancaire compteBancaire=compteBancaireRepository.findById(id).orElse(null);
        Alias alias = compteBancaire.getAlias();
        return alias;
    }
}
