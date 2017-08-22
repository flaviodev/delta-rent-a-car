package br.edu.faculdadedelta.posexemplofdsdev.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import br.edu.faculdadedelta.posexemplofdsdev.model.type.StatusDoProduto;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = -1466134548885395261L;

	
	public Produto() {}

	public Produto(Long id, String nome, BigDecimal valor, Date dataDeCadastro, StatusDoProduto statusDoProduto) {
		super();
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.dataDeCadastro = dataDeCadastro;
		this.statusDoProduto = statusDoProduto;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo nome não pode ser vazio!")
	private String nome;
	
	@Column(nullable = false, precision = 10, scale = 2)
	@NumberFormat(pattern = "#,##0.00")
	@NotNull(message = "O campo valor não pode ser vazio!")
	private BigDecimal valor;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O campo data de cadastro não pode ser vazio!")
	private Date dataDeCadastro;

	@Enumerated(EnumType.STRING)
	private StatusDoProduto statusDoProduto;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public StatusDoProduto getStatusDoProduto() {
		return statusDoProduto;
	}

	public void setStatusDoProduto(StatusDoProduto statusDoProduto) {
		this.statusDoProduto = statusDoProduto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + "]";
	}
	

}
