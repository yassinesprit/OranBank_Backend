package com.bfi.orabank.Services;

import com.bfi.orabank.Entities.Token;
import com.bfi.orabank.Entities.Utilisateur;
import com.bfi.orabank.Repositories.TokenRepository;
import com.bfi.orabank.Repositories.UtilisateurRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    private final UtilisateurRepository userRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
    {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        log.info(authHeader);
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Token storedToken = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            Utilisateur user=storedToken.getUser();
            userRepository.save(user);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }

    }
    //@Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(fixedRate = 86400000)
    public void tokenClean(){
        List<Token> tokenList=tokenRepository.findAll();
        int i =0;
        for (Token token:tokenList) {
            if (ChronoUnit.DAYS.between(token.getInsertionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())>=2 ||( token.isExpired() && token.isRevoked())){
                tokenRepository.delete(token);
                log.info("today the token database has been cleaned , we deleted "+ i+ "tokens");
            }
        }

    }



}
