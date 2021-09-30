package com.minhaLojaDeGames.games.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhaLojaDeGames.games.model.categoriaModel;
import com.minhaLojaDeGames.games.repository.categoriaRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@RestController
@RequestMapping("/api/v1/categoria")
@CrossOrigin("*")
public class categoriaController {
	
	private @Autowired categoriaRepository repositorio;
	
	@GetMapping("/todes")
	public ResponseEntity<List<categoriaModel>> getAll(){
		if (repositorio.findAll().isEmpty()) {
			return ResponseEntity.status(204).build();
		}
		else {
			return ResponseEntity.status(200).body(repositorio.findAll());
			
		}
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<categoriaModel> pegarPorId(@PathVariable(value = "id_categoria") Long idCategoria) {
		java.util.Optional<categoriaModel> objetoOptional = repositorio.findById(idCategoria);

		if (objetoOptional.isPresent()) {
			return ResponseEntity.status(200).body(objetoOptional.get());
		} else {
			return ResponseEntity.status(204).build();
		}
	}
	

}
