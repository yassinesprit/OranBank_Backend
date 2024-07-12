package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.ContactDto;
import com.bfi.orabank.Entities.Contact;

import java.util.List;

public interface IcontactService {
    List<Contact> getAllContacts();
    Contact getContactById(int id) throws Exception;
    Contact createContact(ContactDto contactDto) throws Exception;
    Contact updateContact(int id, Contact contact) throws Exception;
    void deleteContact(int id);
    List<Contact> getContactsByUtilisateurUsername(String s);

}
