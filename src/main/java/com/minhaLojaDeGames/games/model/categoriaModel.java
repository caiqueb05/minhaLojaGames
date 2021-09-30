package com.minhaLojaDeGames.games.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


/**
 * Classe espelho da tabela categoria no banco db_minha_loja_games.
 * 
 * @author Lucas
 * @since 1.0
 *
 */

@Entity
@Table(name = "tb_categoria")
public class categoriaModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	
	@NotBlank
	private String tipoConsole;//tipo de console
	
	@OneToMany
	
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTipoConsole() {
		return tipoConsole;
	}

	public void setTipoConsole(String tipoConsole) {
		this.tipoConsole = tipoConsole;
	}
	
	
}