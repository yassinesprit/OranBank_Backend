package com.bfi.orabank.Config;


import com.bfi.orabank.Security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
            config.addAllowedOrigin("http://localhost:4200");
            return config;
        })).authorizeHttpRequests(authz ->
                authz
                        .requestMatchers("/authentication/**")
                        .permitAll()
                        .requestMatchers("/ws/**")
                        .permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session ->
                        session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/login") // Redirect after successful logout
                .invalidateHttpSession(true) // Invalidate session
                .logoutSuccessHandler((request, response, authentication) -> {
                    request.getHeader("Authorization"); // add Accept header
                    log.info("teeeeeeest" + request.getHeader("Authorization"));
                    SecurityContextHolder.clearContext();
                }))

        ;
        return http.build();
    }
}