package com.bfi.orabank.Controllers;

import com.bfi.orabank.DTO.ContactDto;
import com.bfi.orabank.Entities.Contact;
import com.bfi.orabank.Services.IcontactService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {

    private IcontactService contactService;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable int id) throws Exception {
        Contact contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PostMapping
    public Contact createContact(@RequestBody ContactDto contact) throws Exception {
        return contactService.createContact(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable int id, @RequestBody Contact contactDetails) throws Exception {
        Contact updatedContact = contactService.updateContact(id, contactDetails);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/utilisateur/{s}")
    public ResponseEntity<Object> getContactsByUtilisateurUsername(@PathVariable String s) {
        Assert.notNull(s,"username est null");
        return ResponseEntity.ok(contactService.getContactsByUtilisateurUsername(s));
    }
    }