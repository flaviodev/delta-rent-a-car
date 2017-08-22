package br.edu.faculdadedelta.posexemplofdsdev.model.type;

public enum StatusDoProduto {
	DISPONINVEL("Disponível"), 
	INDISPONIVEL("Indisponível"), 
	NAO_CATALOGADO("Não Catalogado");
	
	private String descricao;
	
	private StatusDoProduto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
