package com.minhaLojaDeGames.games.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.minhaLojaDeGames.games.model.produtoModel;
import com.minhaLojaDeGames.games.repository.produtoRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<produtoModel> getById(@PathVariable(value = "id_produto") long idProduto) {
        return repositorio.findById(idProduto).map(resp -> ResponseEntity.status(200).body(resp))
                .orElse(ResponseEntity.status(400).build());
    }

    @PostMapping("/salvar")
    public ResponseEntity<produtoModel> salvar(@Valid @RequestBody produtoModel novoProduto) {
        return ResponseEntity.status(201).body(repositorio.save(novoProduto));
    /*public ResponseEntity<Object> save(@Valid @RequestBody produtoModel novoProduto) {
        return repositorio.save(novoProduto).map(resp -> ResponseEntity.status(201).body(resp))
                .orElse(ResponseEntity.status(400).build());
    }*/
    }

    @PutMapping("/atualizar")
    public ResponseEntity<produtoModel> atualizar(@Valid @RequestBody produtoModel novoProduto) {
        return ResponseEntity.status(201).body(repositorio.save(novoProduto));

    }

    @DeleteMapping("/deletar/{id_produto}")
    public ResponseEntity<produtoModel> deletar(@PathVariable(value = "id_produto") Long idProduto) {
        Optional<produtoModel> objetoOptional = repositorio.findById(idProduto);

        if (objetoOptional.isPresent()) {
            repositorio.deleteById(idProduto);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

}
