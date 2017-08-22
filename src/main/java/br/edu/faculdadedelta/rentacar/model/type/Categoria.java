package br.edu.faculdadedelta.rentacar.model.type;

public enum Categoria {
	HATCH("Hash"), 
	SEDAN("Sedan"), 
	UTILITARIO("Utilitário"),
	ESPORTIVO("Esportivo");
	
	private String descricao;
	
	private Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
