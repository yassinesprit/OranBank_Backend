package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.AliasRequest;
import com.bfi.orabank.Entities.Alias;
import java.util.List;


public interface IServiceAlias {
    Alias createAlias(AliasRequest alias) throws Exception;

    List<Alias> retrieveAliass ();

    Alias  retrieveAliasById (int idAlias);

    Alias loadAliasByClient (int s);
    Alias loadAliasByCompteBancaireId(int id);

}
