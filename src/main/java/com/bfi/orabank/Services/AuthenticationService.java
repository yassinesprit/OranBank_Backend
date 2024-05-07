package com.bfi.orabank.Services;


import com.bfi.orabank.DTO.AuthenticationRequest;
import com.bfi.orabank.DTO.AuthenticationResponse;
import com.bfi.orabank.Entities.Token;
import com.bfi.orabank.Entities.TokenType;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.Repositories.TokenRepository;
import com.bfi.orabank.Repositories.UtilisateurRepository;
import com.bfi.orabank.Util.JWTUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AuthenticationService implements IAuthenticationService {
    @Autowired
     UtilisateurRepository repository;
    @Autowired
     JWTUtils jwtService;
    @Autowired

     AuthenticationManager authenticationManager;
    @Autowired

     TokenRepository tokenRepository;



    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws Exception {

        if (AuthenticationRequestValidator(request)) {
            if (repository.findByUsername(request.getUsername()).isAccountNonLocked()) {
                log.info("aa" + request.getUsername() + request.getPassword());

                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );

                // Check if authentication was successful
                if (authentication.isAuthenticated()) {
                    Utilisateur user = repository.findByUsername(request.getUsername());
                    log.info("1");
                    String jwtToken = jwtService.generateToken(user);
                    log.info("2");
                    revokeAllUserTokens(user);
                    log.info("3");
                    saveUserToken(user, jwtToken);
                    return AuthenticationResponse.builder()
                            .token(jwtToken)
                            .build();
                } else {
                    throw new Exception("Invalid password");
                }
            } else {
                throw new Exception("Your account is locked");
            }
        }
        return null;
    }

    private void saveUserToken(Utilisateur user, String jwtToken) {
        Token token=Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .insertionDate(new Date())
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(Utilisateur user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public static boolean AuthenticationRequestValidator(AuthenticationRequest request) throws Exception{
        if (request.getPassword()==null){
            throw new Exception("Password is empty");
        }
        if (request.getUsername()==null){
            throw new Exception("Username is empty");
        }
        return true;
    }
}
