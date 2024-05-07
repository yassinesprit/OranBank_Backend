package com.bfi.orabank.Services;

import com.bfi.orabank.DTO.AuthenticationRequest;
import com.bfi.orabank.DTO.AuthenticationResponse;


public interface IAuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception;

    }
