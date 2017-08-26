package br.edu.faculdadedelta.rentacar.services;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.faculdadedelta.rentacar.model.EntidadeBase;

public abstract class CRUDServicoBase<ID extends Serializable, E extends EntidadeBase<ID>, R extends JpaRepository<E, ID>> {

	@Autowired
	private R repositorio;

	@SuppressWarnings("unchecked")
	public E obterNovo() {
		E entidade = null;

		try {
			entidade = ((Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
					.getActualTypeArguments()[1]).newInstance();

		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Erro ao instanciar a entidade");
		}

		return entidade;
	}

	public E obter(ID id) {
		return repositorio.findOne(id);
	}

	public void excluir(ID id) {
		repositorio.delete(id);
	}

	public E salvar(E entidade) {
		return repositorio.save(entidade);
	}

	public final R getRepositorio() {
		return repositorio;
	}

	public List<E> listarTodos() {
		return repositorio.findAll();
	}

	public abstract String validaSeEntidadePodeSerExcluida(ID id);
}
