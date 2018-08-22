package br.com.encoder.unidade;

public class UnidadeSugestaoEmpresa {
	private String nomeEmpresa;
	private String foneEmpresa;
	private String nomeFuncionario;
	private String comentariosObservacoes;
	private int cliente;

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getFoneEmpresa() {
		return foneEmpresa;
	}

	public void setFoneEmpresa(String foneEmpresa) {
		this.foneEmpresa = foneEmpresa;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getComentariosObservacoes() {
		return comentariosObservacoes;
	}

	public void setComentariosObservacoes(String comentariosObservacoes) {
		this.comentariosObservacoes = comentariosObservacoes;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

}
