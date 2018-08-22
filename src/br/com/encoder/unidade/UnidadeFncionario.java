package br.com.encoder.unidade;

import android.graphics.Bitmap;

public class UnidadeFncionario {
	private int id;
	private String foto;
	private String nome;
	private String cargo;
	private String categoria;
	private Bitmap fotoFuncionario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Bitmap getFotoFuncionario() {
		return fotoFuncionario;
	}

	public void setFotoFuncionario(Bitmap fotoFuncionario) {
		this.fotoFuncionario = fotoFuncionario;
	}

}
