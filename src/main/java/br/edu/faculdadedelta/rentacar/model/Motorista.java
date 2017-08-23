package br.edu.faculdadedelta.rentacar.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.edu.faculdadedelta.rentacar.model.type.Categoria;
import br.edu.faculdadedelta.rentacar.model.type.Sexo;

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

	@NotNull(message = "O campo sexo não pode ser vazio!")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
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

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getDescricaoSexo() {
		return sexo!=null ? sexo.getDescricao() : null;
	}
	
	@Override
	public String toString() {
		return "Motorista [id=" + id + ", nome=" + nome + "]";
	}
}
