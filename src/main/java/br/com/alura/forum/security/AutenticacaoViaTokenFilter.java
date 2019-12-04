package br.com.alura.forum.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.model.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean valido = tokenService.isTokenValido(token);
		
		if (valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> usuarioAutenticado = usuarioRepository.findById(idUsuario);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuarioAutenticado, null, usuarioAutenticado.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private String recuperarToken(HttpServletRequest request) {
		String headerAuthorization = request.getHeader("Authorization");
		if (headerAuthorization == null 
				|| headerAuthorization.isEmpty()
					|| !headerAuthorization.startsWith("Bearer")
						|| headerAuthorization.split(" ").length != 2) {
			return null;
		}
		
		return  headerAuthorization.split(" ")[1];
	}
}
