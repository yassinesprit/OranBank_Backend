package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.TransfertDto;
import com.bfi.orabank.Entities.Transfert;

import java.util.List;

public interface IServiceTransfert {
    Transfert createTransfert(TransfertDto transfertDto) throws Exception;
    List<Transfert> retrieveTransfertByClient(int clientId);
    Transfert retrieveTransfertById(int id);
}
