package br.edu.faculdadedelta.rentacar.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Carro extends EntidadeBase<Long> {

	private static final long serialVersionUID = -2875674544565344461L;
	
	public Carro() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo placa não pode ser vazio!")
	private String placa;

	@NotBlank(message = "O campo chassi não pode ser vazio!")
	private String chassi;
	
	@NotNull(message = "Ocampo valor da diária não pode ser vazio!")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorDaDiaria;
	
	@NotNull(message = "O campo fabricante não pode ser vazio!")
	@ManyToOne
	private Modelo modelo;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public BigDecimal getValorDaDiaria() {
		return valorDaDiaria;
	}

	public void setValorDaDiaria(BigDecimal valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	@Override
	public String getTextoApresentacao() {
		StringBuilder texto = new StringBuilder("");
		texto.append(placa!=null ? placa : "");
		texto.append(placa!=null ? " - " : "");
		texto.append(modelo!=null ? modelo.getTextoApresentacao() : "");
		
		return texto.toString();
	}
	
	public String getDescricaoDoModelo() {
		return modelo!=null ? modelo.getTextoApresentacao() : null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Carro [id=");
		builder.append(id);
		builder.append("] -> ");
		builder.append(getTextoApresentacao());
		
		return builder.toString();
	}
	
}
