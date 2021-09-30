package com.minhaLojaDeGames.games.repository;

import com.minhaLojaDeGames.games.model.produtoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface produtoRepository extends JpaRepository<produtoModel, Long> {

    public List<produtoModel> findAllByGeneroContainingIgnoreCase(String genero);

}
