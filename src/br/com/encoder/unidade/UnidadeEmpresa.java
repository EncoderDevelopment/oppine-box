package br.com.encoder.unidade;

import java.util.List;

import android.graphics.Bitmap;

public class UnidadeEmpresa {

	private int empr_id;
	private String empr_nome;
	private String empr_site;
	private String empr_logomarca;
	private List<UnidadeCategoriaEmpresa> categorias;
	private List<UnidadeFncionario> funcionarios;
	private Bitmap iconeEmpresa;

	public int getEmpr_id() {
		return empr_id;
	}

	public void setEmpr_id(int empr_id) {
		this.empr_id = empr_id;
	}

	public String getEmpr_nome() {
		return empr_nome;
	}

	public void setEmpr_nome(String empr_nome) {
		this.empr_nome = empr_nome;
	}

	public String getEmpr_site() {
		return empr_site;
	}

	public void setEmpr_site(String empr_site) {
		this.empr_site = empr_site;
	}

	public String getEmpr_logomarca() {
		return empr_logomarca;
	}

	public void setEmpr_logomarca(String empr_logomarca) {
		this.empr_logomarca = empr_logomarca;
	}

	public List<UnidadeCategoriaEmpresa> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<UnidadeCategoriaEmpresa> categorias) {
		this.categorias = categorias;
	}

	public Bitmap getIconeEmpresa() {
		return iconeEmpresa;
	}

	public void setIconeEmpresa(Bitmap iconeEmpresa) {
		this.iconeEmpresa = iconeEmpresa;
	}

	public List<UnidadeFncionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<UnidadeFncionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
