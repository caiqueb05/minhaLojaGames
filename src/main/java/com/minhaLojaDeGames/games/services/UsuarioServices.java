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

import com.minhaLojaDeGames.games.model.UsuarioModel;
import com.minhaLojaDeGames.games.model.dtos.CredenciaisDTO;
import com.minhaLojaDeGames.games.model.dtos.UsuarioLoginDTO;
import com.minhaLojaDeGames.games.repository.UsuarioRepository;



@Service
public class UsuarioServices {

	private @Autowired UsuarioRepository repositorio;

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
	public Optional<Object> cadastrarUsuario(UsuarioModel usuarioParaCadastrar) {
		return repositorio.findByEmailUsuario(usuarioParaCadastrar.getEmailUsuario()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			usuarioParaCadastrar.setSenhaUsuario(encriptadorDeSenha(usuarioParaCadastrar.getSenhaUsuario()));
			return Optional.ofNullable(repositorio.save(usuarioParaCadastrar));
		});

	}

	/**
	 * Metodo utilizado para atualizar usuario no banco
	 * 
	 * @param usuarioParaAtualizar do tipo Usuario
	 * @return Optional com Usuario atualizado
	 * @author Turma34
	 * @since 1.5
	 * 
	 */
	public Optional<UsuarioModel> atualizarUsuario(UsuarioModel usuarioParaAtualizar) {
		return repositorio.findById(usuarioParaAtualizar.getIdUsuario()).map(resp -> {
			resp.setNomeUsuario(usuarioParaAtualizar.getNomeUsuario());
			resp.setSenhaUsuario(encriptadorDeSenha(usuarioParaAtualizar.getSenhaUsuario()));
			return Optional.ofNullable(repositorio.save(resp));
		}).orElseGet(() -> {
			return Optional.empty();
		});

	}

	/**
	 * Metodo utilizado para pegar credenciais do usuario com Tokem (Formato Basic),
	 * este método sera utilizado para retornar ao front o token utilizado para ter
	 * acesso aos dados do usuario e mantelo logado no sistema
	 * 
	 * @param usuarioParaAutenticar do tipo UsuarioLoginDTO necessario email e senha
	 *                              para validar
	 * @return ResponseEntity com CredenciaisDTO preenchido com informações mais o
	 *         Token
	 * @since 1.0
	 * @author Turma34
	 */
	public ResponseEntity<CredenciaisDTO> pegarCredenciais(UsuarioLoginDTO usuarioParaAutenticar) {
		return repositorio.findByEmailUsuario(usuarioParaAutenticar.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(usuarioParaAutenticar.getSenha(), resp.getSenhaUsuario())) {

				CredenciaisDTO objetoCredenciaisDTO = new CredenciaisDTO();

				objetoCredenciaisDTO.setTokenUsuario(gerarToken(usuarioParaAutenticar.getEmail(), usuarioParaAutenticar.getSenha()));
				objetoCredenciaisDTO.setIdUsuario(resp.getIdUsuario());
				objetoCredenciaisDTO.setNomeUsuario(resp.getNomeUsuario());
				objetoCredenciaisDTO.setEmailUsuario(resp.getEmailUsuario());
				objetoCredenciaisDTO.setSenhaUsuario(resp.getSenhaUsuario());

				return ResponseEntity.status(201).body(objetoCredenciaisDTO); // Usuario Credenciado
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha Incorreta!"); // Senha incorreta
			}
		}).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não existe!"); // Email não existe
		});

	}

	/**
	 * Metodo statico utilizado para gerar token
	 * 
	 * @param email
	 * @param senha
	 * @return Token no formato Basic para autenticação
	 * @since 1.0
	 * @author Turma34
	 */
	private static String gerarToken(String email, String senha) {
		String estruturaBasic = email + ":" + senha; // gustavoboaz@gmail.com:134652
		byte[] estruturaBase64 = Base64.encodeBase64(estruturaBasic.getBytes(Charset.forName("US-ASCII"))); // hHJyigo-o+i7%0ÍUG465sas=-
		return "Basic " + new String(estruturaBase64); // Basic hHJyigo-o+i7%0ÍUG465sas=-

	}

	/**
	 * Método estatico que recebe a senha do usuario o criptografa
	 * 
	 * @param senha
	 * @return String da senha criptografada
 	 * @since 1.0
	 * @author Turma34
	 */
	private static String encriptadorDeSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);

	}
}