package com.bfi.orabank.DTO;

import com.bfi.orabank.Entities.TypeAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AliasRequest {
    String description;
    TypeAlias type;
    int clientId;
}
