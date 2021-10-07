package com.minhaLojaDeGames.games.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhaLojaDeGames.games.model.CategoriaModel;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {

	/**
	 * metodo utilizado para relizar pesquisar pela coluna descricao da tabela
	 * categoria
	 * 
	 * 
	 * @param descricao
	 * @return Lista com descricao
	 * @author Leonardo
	 * @since 1.0
	 */
	public List<CategoriaModel> findAllByDescricaoContainingIgnoreCase(String descricao);

}
