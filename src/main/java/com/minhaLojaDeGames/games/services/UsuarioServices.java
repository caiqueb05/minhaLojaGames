package com.minhaLojaDeGames.games.services;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.minhaLojaDeGames.games.model.UserLogin;
import com.minhaLojaDeGames.games.model.UsuarioModel;
import com.minhaLojaDeGames.games.model.dtos.CredenciaisDTO;
import com.minhaLojaDeGames.games.model.dtos.UsuarioLoginDTO;
import com.minhaLojaDeGames.games.repository.UsuarioRepository;



@Service
public class UsuarioServices {

	private @Autowired UsuarioRepository repository;
	
	/**
	 * Metodo utilizado para cadastrar usuário validando duplicidade de email no
	 * banco
	 * 
	 * @param usuarioParaCadastrar do tipo Usuario
	 * @return Optional com Usuario cadastrado caso email não seja existente
	 * @author Turma34
	 * @since 2.0
	 * 
	 */
	public Optional<Object> CadastrarUsuario(UsuarioModel usuarioParaCadastrar) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return repository.findByEmailUsuario(usuarioParaCadastrar.getEmailUsuario()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			usuarioParaCadastrar.setSenhaUsuario(encoder.encode(usuarioParaCadastrar.getSenhaUsuario()));
			return Optional.ofNullable(repository.save(usuarioParaCadastrar));
		});

	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<UsuarioModel> nomeUsuario = repository.findByEmailUsuario(user.get().getEmail());

        if(nomeUsuario.isPresent()){
            if(encoder.matches(user.get().getSenha(), nomeUsuario.get().getSenhaUsuario())) {
                String auth = user.get().getEmail() + ":" + user.get().getSenha();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodeAuth);

                user.get().setToken(authHeader);
                user.get().setNome(nomeUsuario.get().getNomeUsuario());
                user.get().setId(nomeUsuario.get().getIdUsuario());
                user.get().setSenha(nomeUsuario.get().getSenhaUsuario());
                user.get().setUsuario(nomeUsuario.get().getEmailUsuario());

                return user;
            }
        }
        return null;
	}

}