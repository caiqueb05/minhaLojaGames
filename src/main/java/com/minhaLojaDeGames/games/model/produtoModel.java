package com.minhaLojaDeGames.games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Classe espelho da tabela produto no banco db_minha_loja_games.
 * 
 * @author Ronan
 * @since 1.0
 *
 */

@Entity
@Table(name = "tb_produtos")
public class produtoModel {

	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idProduto;
	
	private @NotBlank String nome;
	
	private double preco;
	
	private @NotBlank String genero;
	
	@ManyToOne
	@JsonIgnoreProperties("categoria")
	
	private categoriaModel categoria;
	
	public categoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(categoriaModel categoria) {
		this.categoria = categoria;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
}
