package br.edu.faculdadedelta.rentacar.model.type;

public enum SituacaoDoCarro {
	DISPONIVEL("Disponível"), 
	LOCADO("Locado");
	
	private String descricao;
	
	private SituacaoDoCarro(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
