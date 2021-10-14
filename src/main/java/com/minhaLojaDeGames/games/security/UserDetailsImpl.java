package com.minhaLojaDeGames.games.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.minhaLojaDeGames.games.model.UsuarioModel;

public class UserDetailsImpl implements UserDetails {


	private static final long serialVersionUID = 1L;
	private String email;
	private String senha;
	private List<GrantedAuthority> autorizacoes;
	
	public UserDetailsImpl(UsuarioModel usuario) {
		this.email = usuario.getEmailUsuario();
		this.senha = usuario.getSenhaUsuario();
	}
	
	public UserDetailsImpl() {}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorizacoes;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
