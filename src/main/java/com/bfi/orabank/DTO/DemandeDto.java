package com.bfi.orabank.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class DemandeDto {
    float montant;
    String aliasExp;
    String aliasDest;
    String description;

}
