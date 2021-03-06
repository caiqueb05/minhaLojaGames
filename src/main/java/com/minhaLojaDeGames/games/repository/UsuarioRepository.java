package com.minhaLojaDeGames.games.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhaLojaDeGames.games.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	Optional<UsuarioModel> findByEmailUsuario(String emailUsuario);

}
