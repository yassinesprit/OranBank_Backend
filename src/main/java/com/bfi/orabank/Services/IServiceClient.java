package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Client;

import java.util.List;

public interface IServiceClient {
    Client createClient(Client client);
    List<Client> retrieveAllClient();
    Client retrieveById(int id);
    Client retrieveByAlias(String alias);
    Client retrieveByCompteBancaire(int id);
    Client retrieveByUsername(String s);
}
