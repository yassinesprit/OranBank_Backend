package com.bfi.orabank.Controllers;

import com.bfi.orabank.DTO.AuthenticationRequest;
import com.bfi.orabank.DTO.AuthenticationResponse;
import com.bfi.orabank.Services.IAuthenticationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authentication")
public class LoginController {
    @Autowired
    IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) throws Exception {
        Assert.notNull(request,"request is null");
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
