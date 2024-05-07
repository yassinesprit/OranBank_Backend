package com.bfi.orabank.DTO;

import com.bfi.orabank.Entities.Alias;
import com.bfi.orabank.Entities.Devise;
import com.bfi.orabank.Entities.TypeTransfert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
public class TransfertDto {
    String reference;
    String description;
    float fraisDeTransaction;
    float montant;
    int idDevise;
    int idAlias;
    TypeTransfert typeTransfert;

}
