package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Alias;
import com.bfi.orabank.Entities.Client;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.Repositories.AliasRepository;
import com.bfi.orabank.Repositories.ClientRepository;
import com.bfi.orabank.Repositories.CompteBancaireRepository;
import com.bfi.orabank.Repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IServiceClient{

    ClientRepository clientRepository;
    AliasRepository aliasRepository;
    CompteBancaireRepository compteBancaireRepository;
    UtilisateurRepository utilisateurRepository;

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> retrieveAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client retrieveById(int id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client retrieveByAlias(String alias) {
        Client client=aliasRepository.findByAlias(alias).getClient();
        return client;
    }

    @Override
    public Client retrieveByCompteBancaire(int id) {
        Client client=compteBancaireRepository.findById(id).orElse(null).getClient();
        return client;
    }

    @Override
    public Client retrieveByUsername(String s) {
        Utilisateur utilisateur=utilisateurRepository.findByUsername(s);
        Client client =utilisateur.getClient();
        return client;
    }
}
