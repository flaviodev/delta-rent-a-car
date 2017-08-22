package br.edu.faculdadedelta.rentacar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Fabricante extends Model<Long> {

	private static final long serialVersionUID = -1466134548885395261L;
	
	public Fabricante() {}

	public Fabricante(Long id, String nome, String descricao) {
		super(id);
		this.nome = nome;
		this.descricao = descricao;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome não pode ser vazio!")
	private String nome;
	
	@NotBlank(message = "O campo descrição não pode ser vazio!")
	private String descricao;

	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Fabricante [id=" + getId() + ", nome=" + nome + "]";
	}
	
}
