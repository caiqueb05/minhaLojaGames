package com.minhaLojaDeGames.games.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhaLojaDeGames.games.model.CategoriaModel;
import com.minhaLojaDeGames.games.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/v1/categoria")
@CrossOrigin("*")
public class CategoriaController {

	private @Autowired CategoriaRepository repositorio;

	@GetMapping("/todes")
	public ResponseEntity<List<CategoriaModel>> getAll() {
		if (repositorio.findAll().isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(repositorio.findAll());

		}
	}

	@GetMapping("/{id_categoria}")
	public ResponseEntity<CategoriaModel> pegarPorId(@PathVariable(value = "id_categoria") Long idCategoria) {
		java.util.Optional<CategoriaModel> objetoOptional = repositorio.findById(idCategoria);

		if (objetoOptional.isPresent()) {
			return ResponseEntity.status(200).body(objetoOptional.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}

	@PostMapping("/salvar")
	public ResponseEntity<CategoriaModel> salvar(@Valid @RequestBody CategoriaModel novaCategoria) {
		return ResponseEntity.status(201).body(repositorio.save(novaCategoria));

	}

	@PutMapping("/atualizar") // Arrumar rota Put para atualizar conforme o id passado pela rota
	// public ResponseEntity<categoriaModel> atualizar(@Valid @RequestBody
	// categoriaModel novaCategoria) {
	// return repositorio.findById().map(resp ->
	// ResponseEntity.status(201).body(resp)).orElse(ResponseEntity.status(400).build());
	public Optional<CategoriaModel> atualizarCategoria(CategoriaModel categoriaParaAtualizar) {
		return repositorio.findById(categoriaParaAtualizar.getIdCategoria()).map(resp -> {
			resp.setTipoConsole(categoriaParaAtualizar.getTipoConsole());
			resp.setDescricao(categoriaParaAtualizar.getDescricao());
			return Optional.ofNullable(repositorio.save(resp));
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	

	
	@DeleteMapping("/deletar/{id_categoria}")
	public ResponseEntity<CategoriaModel> deletar(@PathVariable(value = "id_categoria") Long idCategoria) {
		Optional<CategoriaModel> objetoOptional = repositorio.findById(idCategoria);

		if (objetoOptional.isPresent()) {
			repositorio.deleteById(idCategoria);
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(400).build();
		}
	}

}
