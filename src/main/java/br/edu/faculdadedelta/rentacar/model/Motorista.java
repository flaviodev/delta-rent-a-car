package br.edu.faculdadedelta.rentacar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Motorista extends EntidadeBase<Long> {

	private static final long serialVersionUID = -1466345548885235261L;
	
	public Motorista() {}

	public Motorista(Long id, String nome, String cpf, String cnh) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cnh = cnh;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome não pode ser vazio!")
	private String nome;
	
	@NotBlank(message = "O campo CPF não pode ser vazio!")
	private String cpf;

	@NotBlank(message = "O campo CNH não pode ser vazio!")
	private String cnh;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	@Override
	public String toString() {
		return "Motorista [id=" + id + ", nome=" + nome + "]";
	}
}
