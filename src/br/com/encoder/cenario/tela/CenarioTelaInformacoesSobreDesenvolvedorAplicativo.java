package br.com.encoder.cenario.tela;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import br.com.encoder.cenario.menu.CenarioMenuInformacoesSobreDesenvolvedorAplicativo;
import br.com.encoder.componente.ComponenteDialogoGrande;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTextoApp;

public class CenarioTelaInformacoesSobreDesenvolvedorAplicativo extends CCLayer{
	
	/**
	 * Configura o cenario na tela de inicial do jogo
	 */
	public CenarioTelaInformacoesSobreDesenvolvedorAplicativo() {
		addChild(new ComponenteDialogoGrande(ConfiguracaoTextoApp.TEXTO_BOTAO_CABECALHO_SOBRE));
		addChild(new CenarioMenuInformacoesSobreDesenvolvedorAplicativo());
		
	}
	
	/**
	 * Cria a cena e a camada do cenario configurações
	 * @return
	 */
	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaInformacoesSobreDesenvolvedorAplicativo());
		return cena;
	}
	
}
