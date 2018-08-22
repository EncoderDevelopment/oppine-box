package br.com.encoder.cenario.menu;

import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTRTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor3B;

import br.com.encoder.cenario.tela.CenarioTelaInicio;
import br.com.encoder.componente.ComponenteBotao;
import br.com.encoder.componente.ComponenteCampoTexto;
import br.com.encoder.componente.ComponenteImagem;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.encoder.contrato.ContratoBotaoMenu;

public class CenarioMenuInformacoesSobreDesenvolvedorAplicativo extends CCLayer implements ContratoBotaoMenu{
	
	private ComponenteImagem informacoes;
	private ComponenteBotao botaoFechar;
	private final float scaleX = 0.5f;
	private final float scaleY = 0.5f;
	
	public CenarioMenuInformacoesSobreDesenvolvedorAplicativo() {
		this.setIsTouchEnabled(true);
		ConfiguracaoTextoApp.carregaTexto();
		
		criaComponentes();
		botaoFechar.setDelegate(this);
		setPosicao();
		dicionaComponentesNaTela();
	}

	private void dicionaComponentesNaTela() {
		
		informacoes.adicionaComEfeitoDeBulo(5);
		addChild(informacoes);
		
		botaoFechar.adicionaBotaoETextoComEfeitoDeBulo();
		addChild(botaoFechar);
	}

	private void criaComponentes() {
		informacoes = new ComponenteImagem(ConfiguracaoImagemCaminho.SOBRE_APP);
		botaoFechar = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_GENERICO , new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_BOTAO_CABECALHO_FECHAR, ConfiguracaoFontCaminho.FONT_KLONDIKE, ccColor3B.ccWHITE, 26), scaleX , scaleY);
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setPosicao() {
		//posição dos botões
		informacoes.setScale(scaleX);
		informacoes.setPosition(CGPoint.ccp((larguraDaCena() / 2.f) -80, (alturaDaCena() / 2.f) -130));
		botaoFechar.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2 , (alturaDaCena() /2) - 140 )));
		
	}
	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {
		
		//Executa o som ao clicar no botão
		ConfiguracaoPreferencias.vibrarCelular(30);
		
		
		if (sender.equals(botaoFechar)) {
			CCDirector.sharedDirector().replaceScene( CCFadeTRTransition.transition(0.1f , CenarioTelaInicio.criaCenario()));
		}
		
		
	}
	
}
