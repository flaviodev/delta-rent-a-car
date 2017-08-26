package br.edu.faculdadedelta.rentacar.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Locacao;
import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.model.type.SituacaoDoCarro;
import br.edu.faculdadedelta.rentacar.repository.LocacaoRepository;

@Service
public class LocacaoServico extends CRUDServicoBase<Long, Locacao, LocacaoRepository> {

	@Autowired
	private CarroServico carroServico;

	@Override
	public String validaSeEntidadePodeSerExcluida(Long id) {
		return null;
	}

	public int getQuantidadeLocacoesAssociadosAoCarro(Carro carro) {

		return getRepositorio().countByCarro(carro);
	}

	public int getQuantidadeLocacoesAssociadosAoMotorista(Motorista motorista) {

		return getRepositorio().countByMotorista(motorista);
	}

	private long calculaDiasLocados(Locacao locacao) {
		if (locacao != null && locacao.getDataDeDevolucao() == null) {
			long diferenca = Math.abs(new Date().getTime() - locacao.getDataDeLocacao().getTime());
			long diferencaEmDias = diferenca / (24 * 60 * 60 * 1000);
			diferencaEmDias++;

			return diferencaEmDias;
		}

		return 0;
	}

	private BigDecimal calculaValorTotal(Locacao locacao) {
		if (locacao != null && locacao.getDataDeDevolucao() == null) {
			long diferenca = calculaDiasLocados(locacao);

			return locacao.getValorDaDiariaContratada().multiply(new BigDecimal(diferenca));
		}

		return BigDecimal.ZERO;
	}

	public long getDiasLocados(Locacao locacao) {

		return calculaDiasLocados(locacao);
	}

	public BigDecimal getValorAPagarLocacaoEmAberto(Locacao locacao) {

		return calculaValorTotal(locacao);
	}

	@Transactional
	public void registrarDevolucao(Locacao locacao) {

		if (locacao != null && locacao.getDataDeDevolucao() == null) {
			locacao.setDataDeDevolucao(new Date());
			locacao.setValorTotal(calculaValorTotal(locacao));

			Carro carro = locacao.getCarro();
			carro.setSituacao(SituacaoDoCarro.DISPONIVEL);
			carroServico.salvar(carro);

			getRepositorio().save(locacao);
		}
	}

}
