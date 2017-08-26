package br.edu.faculdadedelta.rentacar.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.rentacar.model.Carro;
import br.edu.faculdadedelta.rentacar.model.Locacao;
import br.edu.faculdadedelta.rentacar.model.Motorista;
import br.edu.faculdadedelta.rentacar.model.type.SituacaoDoCarro;
import br.edu.faculdadedelta.rentacar.repository.LocacaoRepository;
import br.edu.faculdadedelta.rentacar.services.CarroServico;
import br.edu.faculdadedelta.rentacar.services.LocacaoServico;
import br.edu.faculdadedelta.rentacar.services.MotoristaServico;

@Controller
@RequestMapping("/" + LocacaoController.NOME_CONTROLADOR)
public class LocacaoController extends ControllerBase<Long, Locacao, LocacaoRepository, LocacaoServico> {

	protected static final String NOME_CONTROLADOR = "locacoes";

	@Autowired
	private MotoristaServico motoristaServico;

	@Autowired
	private CarroServico carroServico;

	@Override
	public String getNomeControlador() {
		return NOME_CONTROLADOR;
	}

	@Override
	public String getNomeTemplateEdicao() {
		return "locacao";
	}

	@Override
	protected String getNomeTemplateListagem() {
		return "listaLocacoes";
	}

	@Override
	protected String getMensagemDeSucessoSalvar() {
		return "Locação efetuada com sucesso!";
	}

	@Override
	public String getNomeEntidade() {
		return "Locação";
	}

	@Override
	public String getNomeEntidadePlural() {
		return "Locações";
	}

	@Override
	public String[] getColunasListagem() {
		return new String[] { "ID", "Motorista", "Carro", "Valor Diária", "Data Locação", "Data Devolução" };
	}

	@Override
	public String[] getAtributosListagem() {
		return new String[] { "id", "nomeDoMotorista", "descricaoDoCarro", "valorDaDiariaContratada", "dataDeLocacao",
				"dataDeDevolucao" };
	}

	@Override
	public ModelAndView novo() {
		ModelAndView mv = super.novo();

		mv.addObject("isConsulta", false);

		return mv;
	}

	@Override
	@Transactional
	public ModelAndView salvar(Locacao entidade, Errors errors, RedirectAttributes redirectAttributes) {
		if (entidade != null && entidade.getCarro() != null) {
			entidade.setValorDaDiariaContratada(entidade.getCarro().getValorDaDiaria());

			Carro carro = entidade.getCarro();
			carro.setSituacao(SituacaoDoCarro.LOCADO);
			carroServico.salvar(carro);
		}

		return super.salvar(entidade, errors, redirectAttributes);
	}

	@RequestMapping(value = "/consultar/{id}", method = RequestMethod.GET)
	public ModelAndView consultar(@PathVariable("id") Long id) {
		Locacao locacao = getServico().obter(id);

		ModelAndView mv = new ModelAndView(getNomeTemplateEdicao());

		mv.addObject(getNomeTemplateEdicao(), locacao);
		mv.addObject("isConsulta", true);

		mv.addObject("diasLocados", getServico().getDiasLocados(locacao));
		mv.addObject("valorAPagar", getServico().getValorAPagarLocacaoEmAberto(locacao));

		return mv;
	}

	@Transactional
	@RequestMapping(value = "/devolver/{id}", method = RequestMethod.GET)
	public ModelAndView devolver(@PathVariable("id") Long id) {
		Locacao locacao = getServico().obter(id);

		ModelAndView mv = new ModelAndView(getNomeTemplateEdicao());

		mv.addObject(getNomeTemplateEdicao(), locacao);
		mv.addObject("isConsulta", true);

		getServico().registrarDevolucao(locacao);

		mv.addObject("mensagem", "Devolução registrada com sucesso!");

		return mv;
	}

	@ModelAttribute("todosMotoristas")
	public List<Motorista> todosMotoristas() {
		return motoristaServico.listarTodos();
	}

	@ModelAttribute("carrosDisponiveis")
	public List<Carro> carrosDisponiveis() {
		return carroServico.getCarrosPelaSituacao(SituacaoDoCarro.DISPONIVEL);
	}
}
