package br.com.encoder.unidade;

import java.util.List;

public class UnidadeCacheEmpresas {
	private int idEmpresa;
	private String nomeEmpresa;
	private List<UnidadeEmpresa> empresas;

	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public List<UnidadeEmpresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<UnidadeEmpresa> empresas) {
		this.empresas = empresas;
	}
	
	

}
