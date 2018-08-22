package br.com.encoder.unidade;

import android.graphics.Bitmap;

public class UnidadeCategoriaEmpresa {

	private int cate_id;
	private String cate_nome;
	private String cate_imagem;
	private Bitmap iconeCategoria;

	public UnidadeCategoriaEmpresa(int id, String categoria, Bitmap icone) {
		this.cate_id = id;
		this.cate_nome = categoria;
		this.iconeCategoria = icone;
	}

	public int getCate_id() {
		return cate_id;
	}

	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}

	public String getCate_nome() {
		return cate_nome;
	}

	public void setCate_nome(String cate_nome) {
		this.cate_nome = cate_nome;
	}

	public String getCate_imagem() {
		return cate_imagem;
	}

	public void setCate_imagem(String cate_imagem) {
		this.cate_imagem = cate_imagem;
	}

	public Bitmap getIconeCategoria() {
		return iconeCategoria;
	}

	public void setIconeCategoria(Bitmap iconeCategoria) {
		this.iconeCategoria = iconeCategoria;
	}

}
