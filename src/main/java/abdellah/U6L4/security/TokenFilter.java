package abdellah.U6L4.security;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.exceptions.UnauthorizedException;
import abdellah.U6L4.services.DipendentiService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final TokenTools tokenTools;
    private final DipendentiService dipendentiservice;

    public TokenFilter(TokenTools tokenTools, DipendentiService dipendentiservice) {
        this.tokenTools = tokenTools;
        this.dipendentiservice = dipendentiservice;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Inserire il token nell'authorization header nel formato corretto");
        String accessToken = authHeader.replace("Bearer ", "");
        tokenTools.verifyToken(accessToken);
        Long userId = this.tokenTools.extractIdfromToken(accessToken);
        Dipendente authenticatedDipendente = this.dipendentiservice.findById(userId);

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticatedDipendente, null, authenticatedDipendente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);


    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());

    }
}