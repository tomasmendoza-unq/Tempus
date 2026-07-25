package edu.ar.tempus.security.jwt.impl;

import edu.ar.tempus.security.jwt.JwtService;
import edu.ar.tempus.security.user.UserDetailsImpl;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("OPTIONS");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null) {
            try {
                autenticarSiCorresponde(token, request);
            } catch (JwtException e) {
                logger.warn(
                        "Token JWT inválido - uri: {}, motivo: {}",
                        request.getRequestURI(),
                        e.getMessage()
                );
                responderError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
                return;
            } catch (UsernameNotFoundException e) {
                logger.warn(
                        "Usuario del token no encontrado - uri: {}, motivo: {}",
                        request.getRequestURI(),
                        e.getMessage()
                );
                responderError(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido o expirado");
                return;
            } catch (Exception e) {
                logger.error(
                        "Error inesperado en el filtro de autenticación JWT - uri: {}",
                        request.getRequestURI(),
                        e
                );
                responderError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private void responderError(HttpServletResponse response, int status, String mensaje) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write("""
            {
              "mensaje": "%s"
            }
            """.formatted(mensaje));
    }

    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }

        return null;
    }

    private void autenticarSiCorresponde(String token, HttpServletRequest request) {
        String username = jwtService.extraerUsername(token);

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (jwtService.tokenValido(token, user)) {
                setAuthenticationContext(user, request);
            }
        }
    }

    private void setAuthenticationContext(UserDetails user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities());

        auth.setDetails(
                new WebAuthenticationDetailsSource()
                        .buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(auth);

        if (user instanceof UserDetailsImpl usuarioDetails) {
            request.setAttribute("userId", usuarioDetails.getId());
        }
    }
}