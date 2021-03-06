package br.edu.faculdadedelta.rentacar.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.faculdadedelta.rentacar.model.type.Categoria;

@Entity
public class Modelo extends EntidadeBase<Long> {

	private static final long serialVersionUID = -2876134548885344461L;
	
	public Modelo() {}

	public Modelo(Long id, String descricao, Categoria categoria,
			Fabricante fabricante) {
		this.id = id;
		this.descricao = descricao;
		this.categoria = categoria;
		this.fabricante = fabricante;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotBlank(message = "O campo descrição não pode ser vazio!")
	private String descricao;
	
	@NotNull(message = "O campo categoria não pode ser vazio!")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@NotNull(message = "O campo fabricante não pode ser vazio!")
	@ManyToOne
	private Fabricante fabricante;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public String getTextoApresentacao() {
		StringBuilder texto = new StringBuilder("");
		texto.append(fabricante!=null ? fabricante.getNome() : "");
		texto.append(fabricante!=null ? "/" : "");
		texto.append(descricao!=null ? descricao : "");
		texto.append(categoria!=null ? " - " : "");
		texto.append(categoria!=null ? categoria.getDescricao() : "");
		
		return texto.toString();
	}
	
	public String getDescricaoDaCategoria() {
		return categoria!=null ? categoria.getDescricao() : null; 
	}

	public String getNomeDoFabricante() {
		return fabricante!=null ? fabricante.getTextoApresentacao() : null; 
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Modelo [id=");
		builder.append(id);
		builder.append("] -> ");
		builder.append(getTextoApresentacao());
		
		return builder.toString();
	}

	

	
}
