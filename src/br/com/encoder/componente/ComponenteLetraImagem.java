package br.com.encoder.componente;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;


/**
 * Adiciona um botão na tela do dispositivo
 * @author andrade
 *
 */
public class ComponenteLetraImagem extends CCLayer{
	private CCSprite botao;
	private String letra;
	
	/**
	 * Cria um botão baseado em uma imagem
	 * @param imagem
	 */
	public ComponenteLetraImagem(String imagem) {
		this.setIsTouchEnabled(true);
		this.botao = CCSprite.sprite(imagem);
		this.addChild(this.botao);
		
	}
	
	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	
	public void adicionaComEfeitoDeFadeESacala(){
		
		float dt = 0.2f;
		botao.setScale(5f);
		CCSpawn removeComEfeitoDesvaneceEscalaTextoBotao = CCSpawn.actions(CCScaleTo.action(dt , 0.5f), CCFadeIn.action(dt));
		botao.runAction(removeComEfeitoDesvaneceEscalaTextoBotao);
	}
	
}
