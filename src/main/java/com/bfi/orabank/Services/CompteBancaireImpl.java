package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Client;
import com.bfi.orabank.Entities.CompteBancaire;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.Repositories.CompteBancaireRepository;
import com.bfi.orabank.Repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CompteBancaireImpl implements IServiceCompteBancaire {

    CompteBancaireRepository compteBancaireRepository;
    UtilisateurRepository utilisateurRepository;

    @Override
    public CompteBancaire createCompteBancaire(CompteBancaire compteBancaire) {
        return compteBancaireRepository.save(compteBancaire);
    }

    @Override
    public List<CompteBancaire> findAllCompteBancaireByClient(int clientId) {
        return compteBancaireRepository.findByClientId(clientId);
    }

    @Override
    public CompteBancaire findCompteById(int compteId) {
        return compteBancaireRepository.findById(compteId).orElse(null);
    }

    @Override
    public CompteBancaire findComptebyAlias(String s) {
        return compteBancaireRepository.findByAliasAlias(s);
    }

    @Override
    public List<CompteBancaire> findCompteBancaireByUsername(String s) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(s);
        Client client = utilisateur.getClient();
        List<CompteBancaire> compteBancaires = client.getCompteBancaires();
        return compteBancaires;
    }

    @Override
    public List<CompteBancaire> findFreeCompteBancaireByUsername(String s) {
        List<CompteBancaire> compteBancaires = findCompteBancaireByUsername(s);

        List<CompteBancaire> filteredCompteBancaires = compteBancaires.stream()
                .filter(compteBancaire -> compteBancaire.getAlias() == null)
                .collect(Collectors.toList());

        return filteredCompteBancaires;
    }


}
