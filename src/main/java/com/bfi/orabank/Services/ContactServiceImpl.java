package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.ContactDto;
import com.bfi.orabank.Entities.CompteBancaire;
import com.bfi.orabank.Entities.Contact;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.Repositories.CompteBancaireRepository;
import com.bfi.orabank.Repositories.ContactRepository;
import com.bfi.orabank.Repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ContactServiceImpl implements IcontactService {

    ContactRepository contactRepository;
    CompteBancaireRepository compteBancaireRepository;
    UtilisateurRepository utilisateurRepository;


    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getContactById(int id) throws Exception {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            return contact.get();
        } else {
            throw new Exception("Contact not found for id :: " + id);
        }
    }

    @Override
    public Contact createContact(ContactDto contactDto) throws Exception {

        log.info(contactDto.getNom());
        log.info(contactDto.getUsername());
        Utilisateur utilisateur = utilisateurRepository.findByUsername(contactDto.getUsername());
        CompteBancaire compteBancaire = compteBancaireRepository.findByNumeroCompte(contactDto.getNumeroCompte());
        List<CompteBancaire> compteBancaireListByCurrentUser = utilisateur.getClient().getCompteBancaires();
        List<Contact> contactList=getContactsByUtilisateurUsername(contactDto.getUsername());
        compteBancaireListByCurrentUser.stream().forEach(
                (compte) -> {
                    if (compte == compteBancaire) {
                        try {
                            throw new Exception("Impossible de faire un contact avec un de vos comptes");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });

        if (compteBancaire == null) {
            throw new Exception("compte bancaire inexistant");
        }
        contactList.stream().forEach(
                (contact )->{
                    if (contact.getCompteBancaire()==compteBancaire){
                        try {
                            throw new Exception("Contact existe déja");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        );
        Contact contact = new Contact();
        contact.setNom(contactDto.getNom());
        contact.setCompteBancaire(compteBancaire);
        contact.setUtilisateur(utilisateur);
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(int id, Contact contact) throws Exception {
        Contact existingContact = getContactById(id);
        existingContact.setNom(contact.getNom());
        existingContact.setCompteBancaire(contact.getCompteBancaire());
        return contactRepository.save(existingContact);
    }

    @Override
    public void deleteContact(int id) {
        contactRepository.deleteById(id);
    }

    @Override
    public List<Contact> getContactsByUtilisateurUsername(String s) {
        return contactRepository.findByUtilisateurUsername(s);
    }
}
