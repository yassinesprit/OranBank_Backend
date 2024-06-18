package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.AliasRequest;
import com.bfi.orabank.Entities.Alias;
import com.bfi.orabank.Entities.Client;
import com.bfi.orabank.Entities.CompteBancaire;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.Repositories.AliasRepository;
import com.bfi.orabank.Repositories.ClientRepository;
import com.bfi.orabank.Repositories.CompteBancaireRepository;
import com.bfi.orabank.Repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class AliasServiceImpl implements IServiceAlias {

    AliasRepository aliasRepository;
    ClientRepository clientRepository;
    CompteBancaireRepository compteBancaireRepository;
    UtilisateurRepository utilisateurRepository;

    @Override
    public Alias createAlias(AliasRequest aliasRequest) throws Exception {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(aliasRequest.getUsername());
        Client client = utilisateur.getClient();
        CompteBancaire compteBancaire = compteBancaireRepository.findById(aliasRequest.getCompteBancaireId()).orElse(null);
        if (aliasRepository.findByCompteBancaireId(aliasRequest.getCompteBancaireId()) == null) {
            if (compteBancaire != null) {
                if (client != null) {
                    Alias alias = new Alias();
                    alias.setClient(client);
                    alias.setAlias(UUID.randomUUID().toString());
                    alias.setDescription(aliasRequest.getDescription());
                    alias.setDateDeCreation(new Date());
                    alias.setStatut(true);
                    alias.setCompteBancaire(compteBancaire);
                    alias.setType(aliasRequest.getType());
                    if (aliasRequest.getTelephone() != "") {
                        alias.setTelephone(aliasRequest.getTelephone());
                    } else {
                        alias.setTelephone(null);
                    }

                    return aliasRepository.save(alias);
                } else throw new Exception("Propriétaire est null");
            } else throw new Exception("compte bancaire null");
        } else throw new Exception("compte bancaire a deja un alias");

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
    public Alias loadAliasByCompteBancaireId(int id) throws Exception {
        CompteBancaire compteBancaire = compteBancaireRepository.findById(id).orElse(null);
        Alias aliass = new Alias();
        if (compteBancaire != null) {
            aliass = aliasRepository.findAll().stream()
                    .filter(alias -> alias.getCompteBancaire().getId() == id)
                    .findFirst()
                    .orElse(null);
        }
        else throw new Exception("compte inexistant");
        if (aliass != null) {
            return aliass;
        } else {
            throw new Exception("pas d'alias");
        }

    }
}
