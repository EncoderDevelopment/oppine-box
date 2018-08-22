package br.com.encoder.lista.item;

public class ItemListaEmpresas {
	private String texto;
	private int iconeRid;

	public ItemListaEmpresas() {
		this("", -1);
	}

	public ItemListaEmpresas(String texto, int iconeRid) { this.texto = texto; this.iconeRid = iconeRid; }

	public int getIconeRid() {
		return iconeRid;
	}

	public void setIconeRid(int iconeRid) {
		this.iconeRid = iconeRid;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
