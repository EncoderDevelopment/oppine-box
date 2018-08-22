package br.com.encoder.configuracao.dispositivo;

import br.com.oppinebox.R;

public class ConfiguracaoTextoApp {
	
	public static String TEXTO_BOTAO_APRESENTACAO;
	public static String TEXTO_BOTAO_CABECALHO_FECHAR;
	
	
	public static String TEXTO_BOTAO_CABECALHO_SOBRE;
	public static String TEXTO_BOTAO_CABECALHO_AJUDA;
	public static String TEXTO_BOTAO_CABECALHO_SAIR;
	public static String TEXTO_BOTAO_CABECALHO_SIM;
	public static String TEXTO_BOTAO_CABECALHO_NAO;
	
	public static String TEXTO_CONFIRMACAO_SAIR1;
	public static String TEXTO_CONFIRMACAO_SAIR2;
	
	public static String TEXTO_BOTAO_CABECALHO_CANCELAR;
	
	public static String TEXTO_OPCAO_VOLTAR_DESABILITADO;
	
	public static String ESCOLHA_MELHOR_ATENDIMENTO;
	public static String INFORME_COMO_FOI_ATENDIDO;
	public static String COMO_FUNCIONA;
	
	
	
	public static void carregaTexto() {
		
			TEXTO_BOTAO_APRESENTACAO = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_botao_apresentacao);
			TEXTO_BOTAO_CABECALHO_FECHAR = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_botao_fechar);
			
			TEXTO_BOTAO_CABECALHO_SOBRE = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_cabecalho_sobre);
			TEXTO_BOTAO_CABECALHO_AJUDA = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_cabecalho_ajuda);
			TEXTO_BOTAO_CABECALHO_SAIR = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_cabecalho_sair);
			
			TEXTO_BOTAO_CABECALHO_SIM = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_botao_sim);
			TEXTO_BOTAO_CABECALHO_NAO = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_botao_nao);
			
			TEXTO_CONFIRMACAO_SAIR1 = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_confirmacao_sair1);
			TEXTO_CONFIRMACAO_SAIR2 = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_confirmacao_sair2);
			
			TEXTO_BOTAO_CABECALHO_CANCELAR = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_botao_cancelar);
			
			TEXTO_OPCAO_VOLTAR_DESABILITADO = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_texto_opcao_voltar_desabilitado);
			
			ESCOLHA_MELHOR_ATENDIMENTO = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_escolha_melhor_atendimento);
			INFORME_COMO_FOI_ATENDIDO = ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_informe_como_foi_atendido);
			
			COMO_FUNCIONA= ConfiguracaoPreferencias.CONTEXTO.getString(R.string.ptbr_como_funciona);
			
			
	}
	
	
	
}
