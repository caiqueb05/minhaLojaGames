package com.minhaLojaDeGames.games.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.minhaLojaDeGames.games.model.produtoModel;
import com.minhaLojaDeGames.games.repository.produtoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin("*")
public class produtoController {

    private @Autowired produtoRepository repositorio;

    @GetMapping("/todos")
    public ResponseEntity<List<produtoModel>> getAll(){
        if (repositorio.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        else {
            return ResponseEntity.status(200).body(repositorio.findAll());

        }
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<produtoModel> pegarPorId(@PathVariable(value = "id_produto") Long idProduto) {
        java.util.Optional<produtoModel> objetoOptional = repositorio.findById(idProduto);

        if (objetoOptional.isPresent()) {
            return ResponseEntity.status(200).body(objetoOptional.get());
        } else {
            return ResponseEntity.status(204).build();
        }
    }

}
