package br.com.encoder.cenario.menu;

import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;


import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import android.content.Intent;
import br.com.encoder.ListaCategoriasFragmentActivity;
import br.com.encoder.TutorialInicialFragmentActivity;
import br.com.encoder.cenario.tela.CenarioTelaInicio;
import br.com.encoder.componente.ComponenteBotao;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.encoder.contrato.ContratoBotaoMenu;

public class CenarioMenuApresentacao extends CCLayer implements ContratoBotaoMenu{
	
	private static ComponenteBotao botaoFundo;
	private static ComponenteBotao botaoLogoApresentacao;
	 private CCScene cenarioMenuInicio;
	private int tempoInicio = 1;
	//private static ComponenteBotao botaoEntrar;
	
	public CenarioMenuApresentacao() {
		this.setIsTouchEnabled(true);
		
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		delegaComponentes();
		setButtonspPosition();
		adicionaComponentesNaTela();
		cenarioMenuInicio = CenarioTelaInicio.criaCenario();
		
		this.schedule("contaTempo" , 1f);
		
		
	}

	private void adicionaComponentesNaTela() {
		addChild(botaoFundo);
		
		botaoLogoApresentacao.adicionaBotaoComEfeitoDeBulo();
		addChild(botaoLogoApresentacao);
		
		/*botaoEntrar.adicionaBotaoETextoComEfeitoDeBulo();
		addChild(botaoEntrar);
		
		this.schedule("pulaComTempo" , 2f);*/
	}
	
	/*public void pulaComTempo(float dt){
		removeChild(botaoEntrar, true);
		botaoEntrar.adicionaBotaoETextoComEfeitoDeBulo();
		addChild(botaoEntrar);
		
	}*/
	
	public void contaTempo(float dt){
		System.out.println("tempo de redirect: " + tempoInicio);
		tempoInicio++;

		if(tempoInicio>=6){
			CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , cenarioMenuInicio));
		}

	}
	
	private void delegaComponentes() {
		botaoFundo.setDelegate(this);
		botaoLogoApresentacao.setDelegate(this);
		//botaoEntrar.setDelegate(this);
	}

	private void criaComponentes() {
		botaoFundo = new ComponenteBotao(ConfiguracaoImagemCaminho.FUNDO_CENARIO_SPLASH);
		botaoLogoApresentacao = new ComponenteBotao(ConfiguracaoImagemCaminho.LOGO_PRINCIPAL);
		//botaoEntrar = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_GENERICO , new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_BOTAO_APRESENTACAO, ConfiguracaoFontCaminho.FONT_KLONDIKE, ccColor3B.ccWHITE, 30)  , 1f , 0.7f);
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setButtonspPosition() {
		botaoFundo.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2f, alturaDaCena() / 2f)));
		botaoLogoApresentacao.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2f, alturaDaCena() / 2f)));
		//botaoEntrar.setPosition(resolucao(CGPoint.ccp( larguraDaCena() / 2f , alturaDaCena() - 390)));
	}
	
	
	/**
 	* Adiciona os eventos de click do menu
 	*/
	@Override
	public void clickBotao(ComponenteBotao sender) {
		//Executa o som ao clicar no botão
		ConfiguracaoPreferencias.vibrarCelular(30);
		CCDirector.sharedDirector().replaceScene( CCFadeTransition.transition(1f , CenarioTelaInicio.criaCenario()));			
		
	}
	
	public static void desabilitaToqueBotoes(boolean habilitaDesabilita) {
		botaoFundo.setIsTouchEnabled(habilitaDesabilita);
		botaoLogoApresentacao.setIsTouchEnabled(habilitaDesabilita);
		//botaoEntrar.setIsTouchEnabled(habilitaDesabilita);
	}
	
	
}
