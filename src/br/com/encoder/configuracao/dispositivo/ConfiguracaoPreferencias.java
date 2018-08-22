package br.com.encoder.configuracao.dispositivo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import br.com.encoder.unidade.UnidadeCacheEmpresas;
import br.com.encoder.unidade.UnidadeEmpresa;

public class ConfiguracaoPreferencias {

	// OPÇÕES DE IDIOMA
	public static String PT_BR = "pt/";

	public static int PTBR = 1;

	public static Context CONTEXTO;
	public static int CENA_CORRENTE;
	public static boolean SAIR_MENU_PRINCIAL = false;
	public static boolean VISUALIZA_SPLASH = true;
	public static List<UnidadeCacheEmpresas> CACHE_EMPRESAS = new ArrayList<UnidadeCacheEmpresas>();
	public static List<UnidadeEmpresa> EMPRESAS = new ArrayList<UnidadeEmpresa>();
	public static boolean VISUALIZA_TUTORIAL;

	// recebe o contexto da activity corrente
	public static void delegaContexto(Context contexto) {
		CONTEXTO = contexto;
	}

	// configura o fibrador do celular conforme o tempo passado
	public static void vibrarCelular(long milliseconds) {
		Vibrator rr = (Vibrator) ConfiguracaoPreferencias.CONTEXTO.getSystemService(Context.VIBRATOR_SERVICE);
		rr.vibrate(milliseconds);
	}

	// verifica a cena corrente nas cenas do criadas com cocos2d
	public static void identificaCenaCorrente(int cenaCorrente) {
		CENA_CORRENTE = cenaCorrente;
	}

	// mostra ou esconde a tela de spalsh
	public static void visualizaCenarioSplash(boolean isVisualiza) {
		VISUALIZA_SPLASH = isVisualiza;
	}

	// chama o pop-up sair na quando clicado no menu principal
	public static void sairMenuPrincipal(boolean isSair) {
		SAIR_MENU_PRINCIAL = isSair;
	}

	public static boolean verificaEmpresasEmCache(int id, String nome) {
		//if (((id == ID_EMPRESA) && ID_EMPRESA != 0)) {
			
			EMPRESAS = new ArrayList<UnidadeEmpresa>();
			EMPRESAS.addAll(buscaEmpresasCache(id, nome));
			return true;
			
		//} else if (!nome.isEmpty()) {
			//if (NOME_EMPRESA.equals(nome)) {
				//EMPRESAS = new ArrayList<UnidadeEmpresa>();
				//EMPRESAS.addAll(buscaEmpresasCache(id, nome));
				//return true;
			//}
			//return false;
		//} else {
			//return false;
		//}
	}

	public static void guardaEmpresas(List<UnidadeEmpresa> empresas , int id, String nome) {
		UnidadeCacheEmpresas u = new UnidadeCacheEmpresas();
		u.setIdEmpresa(id);
		u.setNomeEmpresa(nome);
		u.setEmpresas(empresas);
		
		boolean b = true;
		for (UnidadeCacheEmpresas e : CACHE_EMPRESAS) {
			if (e.getIdEmpresa() == id || e.getNomeEmpresa().equals(nome))
				b = false;
				break;
		}
		
		if (b)
			CACHE_EMPRESAS.add(u);
	}

	public static List<UnidadeEmpresa> buscaEmpresasCache(int id, String nome) {
		if (id != 0) {
			for (UnidadeCacheEmpresas u : CACHE_EMPRESAS) {
				if (id == u.getIdEmpresa()) {
					return u.getEmpresas();
				}
			}
		} else if (nome != null && !nome.isEmpty()) {
			for (UnidadeCacheEmpresas u : CACHE_EMPRESAS) {
				if (nome.equals(u.getNomeEmpresa())) {
					return u.getEmpresas();
				}
			}
		}
		return new ArrayList<UnidadeEmpresa>();
	}

	public static void salvaPreferencias(){
		SharedPreferences configuracoes = CONTEXTO.getSharedPreferences("preferencias" , Context.MODE_PRIVATE);
		SharedPreferences.Editor  editor = configuracoes.edit();
		editor.putBoolean("tutorial", false);
		editor.commit();
		
	}

	public static void setVISUALIZA_TUTORIAL(boolean vISUALIZA_TUTORIAL) {
		VISUALIZA_TUTORIAL = vISUALIZA_TUTORIAL;
	}
	
	
}
