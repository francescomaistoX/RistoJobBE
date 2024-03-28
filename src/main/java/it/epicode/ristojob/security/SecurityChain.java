package it.epicode.ristojob.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityChain {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/azienda/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/collaboratore/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/annuncio").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/annuncio/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/").denyAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/candidatura/**").permitAll());
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/recensione/**").permitAll());
        return httpSecurity.build();
    }



}