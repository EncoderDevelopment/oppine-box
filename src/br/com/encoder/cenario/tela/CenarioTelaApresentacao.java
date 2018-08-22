package br.com.encoder.cenario.tela;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;

import br.com.encoder.cenario.menu.CenarioMenuApresentacao;

public class CenarioTelaApresentacao extends  CCLayer{
	
	/**
	 * Configura o cenario na tela de inicial, tela de apresentação do jogo
	 */
	public CenarioTelaApresentacao() {
		//Adiciona ao cenario inicial de apresentacao
		this.addChild(new CenarioMenuApresentacao());
		
	}

	
	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaApresentacao());
		return cena;
	}
	
}
