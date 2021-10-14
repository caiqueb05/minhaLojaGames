package com.minhaLojaDeGames.games.model;

public class UserLogin {
	
		private long Id;

		private String nome;
		
		private String usuario;
		
		private String senha;
		
		private String token;
		
		private String email;

		public long getId() {
			return Id;
		}

		public void setId(long id) {
			Id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getUsuario() {
			return usuario;
		}

		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		
		
		
	}


