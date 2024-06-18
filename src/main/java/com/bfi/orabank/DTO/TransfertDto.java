package com.bfi.orabank.DTO;

import com.bfi.orabank.Entities.Alias;
import com.bfi.orabank.Entities.Devise;
import com.bfi.orabank.Entities.StatusTransfert;
import com.bfi.orabank.Entities.TypeTransfert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@ToString
public class TransfertDto {
    String reference;
    String description;
    int montant;
    int idDevise;
    String destinataireAlias;
    TypeTransfert typeTransfert;
    StatusTransfert statusTransfert;
    String nomBanque;
    String paysBanque;
    String referenceBanque;
    String nomInstitutFin;
    String expediteurAlias;
}
