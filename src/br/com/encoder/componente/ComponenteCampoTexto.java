package br.com.encoder.componente;

import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.types.ccColor3B;

public class ComponenteCampoTexto extends CCLayer{
	/**
	 * Recebe o caminho da imagem
	 * @param imagem
	 */
	private CCLabel palavra;
	
	public ComponenteCampoTexto(String texto , String fonte , ccColor3B cor , float tamanhoFonte) {
		palavra = CCLabel.makeLabel(texto, fonte, tamanhoFonte);
		palavra.setColor(cor);
		addChild(palavra);
	}
	
	
	public ComponenteCampoTexto() {
		// TODO Auto-generated constructor stub
	}


	public void adicionaComEfeitoDeBulo(){
		float dt = 0.8f;
		palavra.runAction(CCSpawn.actions(CCJumpBy.action(dt, palavra.getPosition(), 30f, 5), CCFadeIn.action(dt)));
	}
	
}
