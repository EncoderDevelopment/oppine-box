package br.com.encoder.cenario.tela;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import br.com.encoder.cenario.menu.CenarioMenuInformacoesAjudaAplicativo;
import br.com.encoder.componente.ComponenteDialogoGrande;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTextoApp;

public class CenarioTelabotaoInformacoesAjudaAplicativo extends CCLayer{
	
	/**
	 * Configura o cenario na tela de inicial do jogo
	 */
	public CenarioTelabotaoInformacoesAjudaAplicativo() {
		addChild(new ComponenteDialogoGrande(ConfiguracaoTextoApp.TEXTO_BOTAO_CABECALHO_AJUDA));
		addChild(new CenarioMenuInformacoesAjudaAplicativo());
		
	}
	
	/**
	 * Cria a cena e a camada do cenario configurações
	 * @return
	 */
	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelabotaoInformacoesAjudaAplicativo());
		return cena;
	}
	
}
