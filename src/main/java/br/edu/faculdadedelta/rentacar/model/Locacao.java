package br.edu.faculdadedelta.rentacar.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Locacao extends EntidadeBase<Long> {

	private static final long serialVersionUID = -3475674544565344461L;
	
	private final SimpleDateFormat FORMATADOR_DATA =  new SimpleDateFormat("dd/MM/yyyy");
	
	public Locacao() {
		this.dataDeLocacao = new Date();
	}

	public Locacao(Long id, BigDecimal valorDaDiariaContratada, BigDecimal valorTotal, Date dataDeLocacao,
			Date dataDeDevolucao, Motorista motorista, Carro carro) {
		this.id = id;
		this.valorDaDiariaContratada = valorDaDiariaContratada;
		this.valorTotal = valorTotal;
		this.dataDeLocacao = dataDeLocacao;
		this.dataDeDevolucao = dataDeDevolucao;
		this.motorista = motorista;
		this.carro = carro;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorDaDiariaContratada;
	
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal valorTotal;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDeLocacao;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDeDevolucao;

	@NotNull(message = "O campo motorista não pode ser vazio!")
	@ManyToOne
	private Motorista motorista;

	@NotNull(message = "O campo carro não pode ser vazio!")
	@ManyToOne
	private Carro carro;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public BigDecimal getValorDaDiariaContratada() {
		return valorDaDiariaContratada;
	}

	public void setValorDaDiariaContratada(BigDecimal valorDaDiariaContratada) {
		this.valorDaDiariaContratada = valorDaDiariaContratada;
	}

	public Date getDataDeLocacao() {
		return dataDeLocacao;
	}

	public void setDataDeLocacao(Date dataDeLocacao) {
		this.dataDeLocacao = dataDeLocacao;
	}

	public Date getDataDeDevolucao() {
		return dataDeDevolucao;
	}

	public void setDataDeDevolucao(Date dataDeDevolucao) {
		this.dataDeDevolucao = dataDeDevolucao;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	@Override
	public String getTextoApresentacao() {
		StringBuilder texto = new StringBuilder("");
		texto.append(motorista!=null ? motorista.getTextoApresentacao() : "");
		texto.append(motorista!=null ? " / " : "");
		texto.append(carro!=null ? carro.getTextoApresentacao() : "");
		texto.append(carro!=null ? " / " : "");
		texto.append(dataDeDevolucao!=null ? FORMATADOR_DATA.format(dataDeLocacao) : "");
		
		return texto.toString();
	}	
	
	public String getNomeDoMotoristaComCpf() {
		return motorista!=null ? motorista.getTextoApresentacao() : null;
	}

	public String getNomeDoMotorista() {
		return motorista!=null ? motorista.getNome() : null;
	}

	
	public String getDescricaoDoCarro() {
		return carro!=null ? carro.getTextoApresentacaoSimplificado() : null;
	}

	public String getDescricaoDoCarroComDiaria() {
		return carro!=null ? carro.getTextoApresentacao() : null;
	}	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Locação [id=");
		builder.append(id);
		builder.append("] -> ");
		builder.append(getTextoApresentacao());
		
		return builder.toString();
	}   
}
