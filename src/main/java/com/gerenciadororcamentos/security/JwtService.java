package com.gerenciadororcamentos.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String CHAVE_SECRETA;

    public String extrairUsuario(String token) {
        return extrairInformacao(token, Claims::getSubject);
    }

    public <T> T extrairInformacao(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extrairTodosOsClaims(token);
        return claimsResolver.apply(claims);
    }

    public String gerarToken(UserDetails usuario) {
        return gerarToken(new HashMap<>(), usuario);
    }

    public String gerarToken(Map<String, Object> informacoesExtras, UserDetails usuario) {
        return Jwts.builder()
                .setClaims(informacoesExtras)
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(getChaveAssinatura(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean tokenValido(String token, UserDetails usuario) {
        final String usuarioExtraido = extrairUsuario(token);
        return (usuarioExtraido.equals(usuario.getUsername())) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token) {
        return extrairDataExpiracao(token).before(new Date());
    }

    private Date extrairDataExpiracao(String token) {
        return extrairInformacao(token, Claims::getExpiration);
    }

    private Claims extrairTodosOsClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getChaveAssinatura())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getChaveAssinatura() {
        return Keys.hmacShaKeyFor(CHAVE_SECRETA.getBytes());
    }
}
