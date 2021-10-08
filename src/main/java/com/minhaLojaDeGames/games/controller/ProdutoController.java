package com.minhaLojaDeGames.games.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.minhaLojaDeGames.games.model.ProdutoModel;
import com.minhaLojaDeGames.games.repository.ProdutoRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin("*")
public class ProdutoController {

    private @Autowired ProdutoRepository repositorio;

    @GetMapping("/todos")
    public ResponseEntity<List<ProdutoModel>> getAll(){
        if (repositorio.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        else {
            return ResponseEntity.status(200).body(repositorio.findAll());

        }
    }

    @GetMapping("/{id_produto}")
    public ResponseEntity<ProdutoModel> getById(@PathVariable(value = "id_produto") long idProduto) {
        return repositorio.findById(idProduto).map(resp -> ResponseEntity.status(200).body(resp))
                .orElse(ResponseEntity.status(400).build());
    }

    @PostMapping("/salvar")
    public ResponseEntity<ProdutoModel> salvar(@Valid @RequestBody ProdutoModel novoProduto) {
        return ResponseEntity.status(201).body(repositorio.save(novoProduto));
    /*public ResponseEntity<Object> save(@Valid @RequestBody produtoModel novoProduto) {
        return repositorio.save(novoProduto).map(resp -> ResponseEntity.status(201).body(resp))
                .orElse(ResponseEntity.status(400).build());
    }*/
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ProdutoModel> atualizar(@Valid @RequestBody ProdutoModel novoProduto) {
        return ResponseEntity.status(201).body(repositorio.save(novoProduto));

    }

    @DeleteMapping("/deletar/{id_produto}")
    public ResponseEntity<ProdutoModel> deletar(@PathVariable(value = "id_produto") Long idProduto) {
        Optional<ProdutoModel> objetoOptional = repositorio.findById(idProduto);

        if (objetoOptional.isPresent()) {
            repositorio.deleteById(idProduto);
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }

}
