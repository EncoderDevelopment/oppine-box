package br.com.encoder.cenario.menu;

import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor3B;

import android.content.Intent;
import br.com.encoder.cenario.tela.CenarioTelaSair;
import br.com.encoder.cenario.tela.CenarioTelabotaoInformacoesAjudaAplicativo;
import br.com.encoder.ListaCategoriasFragmentActivity;
import br.com.encoder.RankingFuncionariosActivity;
import br.com.encoder.SugerirEmpresaActivity;
import br.com.encoder.VideoControleActivity;
import br.com.encoder.cenario.tela.CenarioTelaInformacoesSobreDesenvolvedorAplicativo;
import br.com.encoder.componente.ComponenteBotao;
import br.com.encoder.componente.ComponenteCampoTexto;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.encoder.contrato.ContratoBotaoMenu;


/**
 * 
 * @author Encoder Development
 * Claase responsável por adicionar os componentes visuais do menu pincipal (feito com o cocos2d)
 */
public class CenarioMenuInicio extends CCLayer implements ContratoBotaoMenu {

	private static ComponenteBotao botaoEscolhaMelhorAtendimento;
	private static ComponenteBotao botaoSugerirEmpresa;
	private static ComponenteBotao botaoVideoTutorial;

	private static ComponenteBotao botaoInformacoesAjudaAplicativo;
	private static ComponenteBotao botaoSobreDesenvolvimentoAplicativo;
	private static ComponenteBotao botaoSair;

	private final float scaleX = 0.9f;
	private final float scaleY = 0.6f;
	private boolean isVsializa = true;
	private final int tamanhoFont = 26;

	/**
	 * cria o cenário de início da classe posConstruct
	 */
	public CenarioMenuInicio() {
		ConfiguracaoPreferencias.visualizaCenarioSplash(false);
		this.setIsTouchEnabled(true);
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		delegaComponentes();
		setButtonspPosition();
		adicionaComponentesNaTela();
		ConfiguracaoPreferencias.identificaCenaCorrente(2);
		
		
		//Salva preferencias de menu
		if (ConfiguracaoPreferencias.SAIR_MENU_PRINCIAL){
			addChild(CenarioTelaSair.criaCenario());
			desabilitaToqueBotoes(false);
		}

	}

	/**
	 * Adicina os botões na tela ()
	 */
	private void adicionaComponentesNaTela() {
		//botaoEscolhaMelhorAtendimento.adicionaBotaoETextoComEfeitoDeBulo();
		addChild(botaoEscolhaMelhorAtendimento);
		addChild(botaoSugerirEmpresa);
		addChild(botaoVideoTutorial);

		addChild(botaoInformacoesAjudaAplicativo);
		addChild(botaoSobreDesenvolvimentoAplicativo);
		addChild(botaoSair);

		//this.schedule("pulaComTempo", 2f);

	}

/*	public void pulaComTempo(float dt) {
		if (isVsializa) {
			removeChild(botaoEscolhaMelhorAtendimento, true);
			botaoEscolhaMelhorAtendimento.adicionaBotaoETextoComEfeitoDeBulo();
			addChild(botaoEscolhaMelhorAtendimento);
		}
	}*/

	
	/**
	 * delega o touchScreen para as imagens, tornando imagens em botões cocos2d
	 */
	private void delegaComponentes() {
		botaoEscolhaMelhorAtendimento.setDelegate(this);
		botaoSugerirEmpresa.setDelegate(this);
		botaoVideoTutorial.setDelegate(this);

		botaoInformacoesAjudaAplicativo.setDelegate(this);
		botaoSobreDesenvolvimentoAplicativo.setDelegate(this);
		botaoSair.setDelegate(this);
	}

	/**
	 * formata os componentes visuais cocos2d
	 */
	private void criaComponentes() {
		botaoEscolhaMelhorAtendimento = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_GENERICO,
				new ComponenteCampoTexto(ConfiguracaoTextoApp.ESCOLHA_MELHOR_ATENDIMENTO,
						ConfiguracaoFontCaminho.FONT_KLONDIKE, ccColor3B.ccWHITE, tamanhoFont),
				scaleX, scaleY);
		botaoSugerirEmpresa = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_GENERICO,
				new ComponenteCampoTexto(ConfiguracaoTextoApp.INFORME_COMO_FOI_ATENDIDO,
						ConfiguracaoFontCaminho.FONT_KLONDIKE, ccColor3B.ccWHITE, tamanhoFont),
				scaleX, scaleY);
		botaoVideoTutorial = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_GENERICO,
				new ComponenteCampoTexto(ConfiguracaoTextoApp.COMO_FUNCIONA, ConfiguracaoFontCaminho.FONT_KLONDIKE,
						ccColor3B.ccWHITE, tamanhoFont),
				scaleX, scaleY);

		botaoInformacoesAjudaAplicativo = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_INFORMACOES_AJUDA);
		botaoSobreDesenvolvimentoAplicativo = new ComponenteBotao(
				ConfiguracaoImagemCaminho.BOTAO_SOBRE_DESENVOLVIMENTO_APLICATIVO);
		botaoSair = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_SAIR);
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setButtonspPosition() {
		botaoEscolhaMelhorAtendimento.setPosition(resolucao(CGPoint.ccp(larguraDaCena() / 2, alturaDaCena() - 205)));
		botaoSugerirEmpresa.setPosition(resolucao(CGPoint.ccp(larguraDaCena() / 2, alturaDaCena() - 270)));
		botaoVideoTutorial.setPosition(resolucao(CGPoint.ccp(larguraDaCena() / 2, alturaDaCena() - 335)));

		botaoInformacoesAjudaAplicativo
				.setPosition(resolucao(CGPoint.ccp((larguraDaCena() / 2) - 80, alturaDaCena() - 440)));
		botaoSobreDesenvolvimentoAplicativo
				.setPosition(resolucao(CGPoint.ccp((larguraDaCena() / 2), alturaDaCena() - 440)));
		botaoSair.setPosition(resolucao(CGPoint.ccp((larguraDaCena() / 2) + 80, alturaDaCena() - 440)));
	}

	/**
	 * Adiciona os eventos de click do menu
	 */
	@Override
	public void clickBotao(ComponenteBotao sender) {
		// Executa o som ao clicar no botão
		// ConfiguracaoSom.somClickBotao();
		ConfiguracaoPreferencias.vibrarCelular(30);

		setButtonspPosition();

		if (sender.equals(botaoEscolhaMelhorAtendimento)) {
			isVsializa = false;

				 Intent categoriasEmpresas = new Intent(CCDirector.sharedDirector().getActivity(),
				            ListaCategoriasFragmentActivity.class);
				 CCDirector.sharedDirector().getActivity().startActivity(categoriasEmpresas);
				 

		}

		if (sender.equals(botaoSugerirEmpresa)) {
			isVsializa = false;
			
			 Intent sugerirEmpresa = new Intent(CCDirector.sharedDirector().getActivity(),
					 SugerirEmpresaActivity.class);
			 CCDirector.sharedDirector().getActivity().startActivity(sugerirEmpresa);
		}

		if (sender.equals(botaoVideoTutorial)) {
			isVsializa = false;
			
		Intent videoTutorial = new Intent(CCDirector.sharedDirector().getActivity(),
		            VideoControleActivity.class);
		 CCDirector.sharedDirector().getActivity().startActivity(videoTutorial);
				 
			
		}

		if (sender.equals(botaoInformacoesAjudaAplicativo)) {
			isVsializa = false;
			onCliKcBotao(botaoInformacoesAjudaAplicativo,
					new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_INFORMACOES_AJUDA_CLICKADO));
			addChild(CenarioTelabotaoInformacoesAjudaAplicativo.criaCenario());
			desabilitaToqueBotoes(false);
		}

		if (sender.equals(botaoSobreDesenvolvimentoAplicativo)) {
			isVsializa = false;
			onCliKcBotao(botaoSobreDesenvolvimentoAplicativo,
					new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_SOBRE_DESENVOLVIMENTO_APLICATIVO_CLICKADO));
			addChild(CenarioTelaInformacoesSobreDesenvolvedorAplicativo.criaCenario());
			desabilitaToqueBotoes(false);
		}

		if (sender.equals(botaoSair)) {
			isVsializa = false;
			onCliKcBotao(botaoSair, new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_SAIR_CLICKADO));
			addChild(CenarioTelaSair.criaCenario());
			desabilitaToqueBotoes(false);
		}

	}

	/**
	 * @param habilitaDesabilita
	 * habilita e dasabilita tock dos botões
	 */
	public static void desabilitaToqueBotoes(boolean habilitaDesabilita) {
		botaoEscolhaMelhorAtendimento.setIsTouchEnabled(habilitaDesabilita);
		botaoSugerirEmpresa.setIsTouchEnabled(habilitaDesabilita);
		botaoVideoTutorial.setIsTouchEnabled(habilitaDesabilita);
		
		botaoInformacoesAjudaAplicativo.setIsTouchEnabled(habilitaDesabilita);
		botaoSobreDesenvolvimentoAplicativo.setIsTouchEnabled(habilitaDesabilita);
		botaoSair.setIsTouchEnabled(habilitaDesabilita);
	}

	/**
	 * 
	 * @param botaoRemovido
	 * @param novoBotao
	 * remove e adiciona botão na tela para animação cocos2d
	 */
	private void onCliKcBotao(ComponenteBotao botaoRemovido, ComponenteBotao novoBotao) {
		novoBotao.setDelegate(this);
		novoBotao.setPosition(botaoRemovido.getPosition());
		novoBotao.setIsTouchEnabled(false);
		removeChild(botaoRemovido, true);
		addChild(novoBotao);
	}

}
