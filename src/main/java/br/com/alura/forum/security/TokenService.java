package br.com.alura.forum.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private Long tempoExpiracao;
	
	@Value("${forum.jwt.secret}")
	private String chaveSecreta;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
		Date agora = new Date();
		Date tempoLimite = new Date(agora.getTime() + tempoExpiracao);
		
		return Jwts.builder()
				.setIssuer("API FORUM")
				.setSubject(usuarioAutenticado.getId().toString())
				.setIssuedAt(agora)
				.setExpiration(tempoLimite)
				.signWith(SignatureAlgorithm.HS256, chaveSecreta)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.chaveSecreta).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.chaveSecreta).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}
}
