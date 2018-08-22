package br.com.encoder.cenario.menu;

import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.alturaDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.larguraDaCena;
import static br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo.resolucao;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTRTransition;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor3B;

import android.content.Intent;
import br.com.encoder.cenario.tela.CenarioTelaInicio;
import br.com.encoder.componente.ComponenteBotao;
import br.com.encoder.componente.ComponenteCampoTexto;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoImagemCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTextoApp;
import br.com.encoder.contrato.ContratoBotaoMenu;

public class CenarioMenuSair extends CCLayer implements ContratoBotaoMenu {

	private ComponenteBotao botaoSim;
	private ComponenteBotao botaoNao;
	private ComponenteCampoTexto textoConfirmacao1;
	private ComponenteCampoTexto textoConfirmacao2;

	private final float scaleX = 0.4f;
	private final float scaleY = 0.5f;

	public CenarioMenuSair() {
		this.setIsTouchEnabled(true);
		ConfiguracaoTextoApp.carregaTexto();
		criaComponentes();
		// coloca botões na posição correta
		setPosition();
		adicionaComponentesNaTela();
	}

	private void adicionaComponentesNaTela() {
		textoConfirmacao1.adicionaComEfeitoDeBulo();
		addChild(textoConfirmacao1);

		textoConfirmacao2.adicionaComEfeitoDeBulo();
		addChild(textoConfirmacao2);

		botaoSim.adicionaBotaoComEfeitoDeBulo();
		addChild(botaoSim);

		botaoNao.adicionaBotaoComEfeitoDeBulo();
		addChild(botaoNao);
	}

	private void criaComponentes() {
		textoConfirmacao1 = new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_CONFIRMACAO_SAIR1,
				ConfiguracaoFontCaminho.FONT_SENSATION_REGULAR, ccColor3B.ccBLACK, 18);
		textoConfirmacao2 = new ComponenteCampoTexto(ConfiguracaoTextoApp.TEXTO_CONFIRMACAO_SAIR2,
				ConfiguracaoFontCaminho.FONT_SENSATION_REGULAR, ccColor3B.ccBLACK, 18);

		botaoSim = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_SIM, scaleX, scaleY);
		botaoNao = new ComponenteBotao(ConfiguracaoImagemCaminho.BOTAO_NAO, scaleX, scaleY);

		delegaComportamento();
	}

	private void delegaComportamento() {
		botaoSim.setDelegate(this);
		botaoNao.setDelegate(this);
	}

	/**
	 * Configura a posição dos botões
	 */
	private void setPosition() {
		textoConfirmacao1.setPosition(resolucao(CGPoint.ccp(larguraDaCena() / 2, (alturaDaCena() / 2))));
		textoConfirmacao2.setPosition(resolucao(CGPoint.ccp(larguraDaCena() / 2, (alturaDaCena() / 2) - 20)));

		botaoSim.setPosition(resolucao(CGPoint.ccp((larguraDaCena() / 2) - 60, (alturaDaCena() / 2) - 90)));
		botaoNao.setPosition(resolucao(CGPoint.ccp((larguraDaCena() / 2) + 60, (alturaDaCena() / 2) - 90)));

	}

	/**
	 * Adiciona os eventos de click do menu
	 */
	@Override
	public void clickBotao(ComponenteBotao sender) {

		// Executa o som ao clicar no botão
		ConfiguracaoPreferencias.vibrarCelular(30);

		if (sender.equals(botaoSim)) {

			// System.exit(0);

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			CCDirector.sharedDirector().getActivity().startActivity(intent);
			System.exit(0);
		}

		if (sender.equals(botaoNao)) {
			if (ConfiguracaoPreferencias.CENA_CORRENTE == 1) {
				CenarioMenuApresentacao.desabilitaToqueBotoes(true);
			} else if (ConfiguracaoPreferencias.CENA_CORRENTE == 2) {
				CenarioMenuInicio.desabilitaToqueBotoes(true);
			}
			
			ConfiguracaoPreferencias.sairMenuPrincipal(false);

			CCDirector.sharedDirector()
					.replaceScene(CCFadeTRTransition.transition(0.1f, CenarioTelaInicio.criaCenario()));
		}

	}

	

}
