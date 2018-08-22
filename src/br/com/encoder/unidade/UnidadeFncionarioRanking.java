package br.com.encoder.unidade;

import android.graphics.Bitmap;

public class UnidadeFncionarioRanking {
	private int func_id;
	private String func_nome;
	private String func_foto;
	private float porcentagem;
	private Bitmap fotoFuncionario;

	public int getFunc_id() {
		return func_id;
	}

	public void setFunc_id(int func_id) {
		this.func_id = func_id;
	}

	public String getFunc_nome() {
		return func_nome;
	}

	public void setFunc_nome(String func_nome) {
		this.func_nome = func_nome;
	}

	public String getFunc_foto() {
		return func_foto;
	}

	public void setFunc_foto(String func_foto) {
		this.func_foto = func_foto;
	}

	public float getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(float porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Bitmap getFotoFuncionario() {
		return fotoFuncionario;
	}

	public void setFotoFuncionario(Bitmap fotoFuncionario) {
		this.fotoFuncionario = fotoFuncionario;
	}

}
