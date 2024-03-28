package it.epicode.ristojob.security;


import it.epicode.ristojob.exception.UnAuthorizedException;
import it.epicode.ristojob.model.Utente;
import it.epicode.ristojob.service.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTools jwtTools;
    @Autowired
    private UtenteService utenteService;


@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization==null||!authorization.startsWith("Bearer ")){
            throw new UnAuthorizedException("Token non presente");
        }

        String token = authorization.substring(7);

        jwtTools.validateToken(token);

        String email = jwtTools.extractEmailFromToken(token);

        Utente utente = utenteService.getUtenteByEmail(email);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utente, null);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);

    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String servletPath = request.getServletPath();
        return new AntPathMatcher().match("/azienda/**", servletPath)
                || new AntPathMatcher().match("/collaboratore/**", servletPath)
                || new AntPathMatcher().match("/annuncio/**", servletPath)
                || new AntPathMatcher().match("/candidatura/**", servletPath)
                || new AntPathMatcher().match("/recensione/**", servletPath);

    }

}
