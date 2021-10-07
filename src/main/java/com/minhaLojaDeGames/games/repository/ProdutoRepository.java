package com.minhaLojaDeGames.games.repository;

import com.minhaLojaDeGames.games.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    public List<ProdutoModel> findAllByGeneroContainingIgnoreCase(String genero);

}
