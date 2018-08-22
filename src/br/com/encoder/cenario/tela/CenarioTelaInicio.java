package br.com.encoder.cenario.tela;

import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import br.com.encoder.cenario.menu.CenarioMenuInicio;
import br.com.encoder.componente.ComponenteImagem;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoImagemCaminho;

public class CenarioTelaInicio extends  CCLayer{
	public CenarioTelaInicio() {
		ComponenteImagem imagemCenarioFundoTela = new ComponenteImagem(ConfiguracaoImagemCaminho.FUNDO_CENARIO_MENU);
		imagemCenarioFundoTela.setPosition(CGPoint.ccp(larguraDaCena() / 2.f, alturaDaCena() / 2.f));
		addChild(imagemCenarioFundoTela);
		
		CCSprite logo = CCSprite.sprite(ConfiguracaoImagemCaminho.LOGO_PRINCIPAL2);
		logo.setPosition(CGPoint.ccp( larguraDaCena() / 2.f, alturaDaCena() - 90));
		addChild(logo);
		
		addChild(new CenarioMenuInicio());
		
	}

	public CCScene criaCena(){
		
		CCScene scene = CCScene.node();
		scene.addChild(this);

		return scene;
	}

	public static CCScene criaCenario(){
		CCScene cena = CCScene.node();
		cena.addChild(new CenarioTelaInicio());
		return cena;
	}
	
}
