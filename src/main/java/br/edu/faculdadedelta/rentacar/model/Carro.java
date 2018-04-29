package br.edu.faculdadedelta.rentacar.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import br.edu.faculdadedelta.rentacar.model.type.SituacaoDoCarro;

@Entity
public class Carro extends EntidadeBase<Long> {

	private static final long serialVersionUID = -2875674544565344461L;

	private static final DecimalFormat FORMATADOR_MOEDA = new DecimalFormat("R$ #,###.00");
	
	public Carro() {
		situacao = SituacaoDoCarro.DISPONIVEL;
		this.valorDaDiaria = BigDecimal.ZERO;
	}

	public Carro(Long id, String placa, String chassi, BigDecimal valorDaDiaria, Modelo modelo,
			SituacaoDoCarro situacao) {
		super();
		this.id = id;
		this.placa = placa;
		this.chassi = chassi;
		this.valorDaDiaria = valorDaDiaria;
		this.modelo = modelo;
		this.situacao = situacao;
	}

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
	
	//@NotNull(message = "O campo fabricante não pode ser vazio!")
	@ManyToOne
	private Modelo modelo;
	
	@Enumerated(EnumType.STRING)
	private SituacaoDoCarro situacao;	
	
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
	
	public SituacaoDoCarro getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoDoCarro situacao) {
		this.situacao = situacao;
	}

	@Override
	public String getTextoApresentacao() {
		StringBuilder texto = new StringBuilder("");
		texto.append(placa!=null ? placa : "");
		texto.append(placa!=null ? " - " : "");
		texto.append(modelo!=null ? modelo.getTextoApresentacao() : "");
		texto.append(valorDaDiaria!=null ? " (Diária  " : "");
		texto.append(valorDaDiaria!=null ? FORMATADOR_MOEDA.format(valorDaDiaria) : "");
		texto.append(valorDaDiaria!=null ? ")" : "");
		return texto.toString();
	}

	public String getTextoApresentacaoSimplificado() {
		StringBuilder texto = new StringBuilder("");
		texto.append(placa!=null ? placa : "");
		texto.append(placa!=null ? " - " : "");
		texto.append(modelo!=null ? modelo.getTextoApresentacao() : "");

		return texto.toString();
	}

	public String getDescricaoDoModelo() {
		return modelo!=null ? modelo.getTextoApresentacao() : null;
	}
	
	public String getDescricaoDaSituacao() {
		return situacao!=null ? situacao.getDescricao() : null;
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
