package com.bfi.orabank.DTO;

import com.bfi.orabank.Entities.TypeAlias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class AliasRequest {
    String description;
    TypeAlias type;
    String username;
    String telephone;
    int compteBancaireId;

}
