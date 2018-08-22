package br.com.encoder.cenario.tela;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import br.com.encoder.cenario.menu.CenarioMenuSair;
import br.com.encoder.componente.ComponenteDialogoPequeno;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTextoApp;

public class CenarioTelaSair extends CCLayer{
	
	/**
	 * Configura o cenario na tela de inicial do jogo
	 */
	public CenarioTelaSair() {
		addChild(new ComponenteDialogoPequeno(ConfiguracaoTextoApp.TEXTO_BOTAO_CABECALHO_SAIR));
		addChild(new CenarioMenuSair());
		
	}
	
	/**
	 * Cria a cena e a camada do cenario configurações
	 * @return
	 */
	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaSair());
		return cena;
	}
	
}
