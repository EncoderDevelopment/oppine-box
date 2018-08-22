package br.com.encoder.componente;

import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

public class ComponenteImagem extends CCLayer{
	/**
	 * Recebe o caminho da imagem
	 * @param imagem
	 */
	
	private CCSprite imagem;
	public ComponenteImagem(String caminho) {
		imagem = CCSprite.sprite(caminho);
		addChild(imagem);
	}
	
	
	
	public void adicionaComEfeitoDeBulo(int pulos){
		float dt = 0.8f;
		imagem.runAction(CCSpawn.actions(CCJumpBy.action(dt, imagem.getPosition(), 30f, pulos), CCFadeIn.action(dt)));
	}
	
	public void adicionaComEfeitoDeDesvanecerParaFora(){
		float dt = 0.8f;
		imagem.runAction(CCFadeIn.action(dt));
	}
	
	public void removeComEfeitoDesvaneceEscala(){
		float dt = 0.2f;
		imagem.runAction(CCSpawn.actions(CCScaleBy.action(dt, 0.5f), CCFadeOut.action(dt)));
	}
	
}
