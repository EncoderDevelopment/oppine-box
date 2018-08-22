package br.com.encoder;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import br.com.encoder.cenario.menu.CenarioMenuApresentacao;
import br.com.encoder.cenario.menu.CenarioMenuInicio;
import br.com.encoder.cenario.tela.CenarioTelaApresentacao;
import br.com.encoder.cenario.tela.CenarioTelaInicio;
import br.com.encoder.cenario.tela.CenarioTelaSair;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class OppineBoxActivity extends Activity {
	private CCGLSurfaceView configuracaoTela;
	private CCScene cenarioSair;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ConfiguracaoPreferencias.delegaContexto(OppineBoxActivity.this);

		// definindo orientação como landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// deixa a tela do dispositivo em modo FULL SCREEN
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		SharedPreferences configuracoeSalva = getSharedPreferences("preferencias", Context.MODE_PRIVATE);

		// busca e adiciona o parametro salvo no arquivo de configuração, cria
		// uma nova com a opção defaut caso não exista
		ConfiguracaoPreferencias.setVISUALIZA_TUTORIAL(configuracoeSalva.getBoolean("tutorial", true));

		if (ConfiguracaoPreferencias.VISUALIZA_TUTORIAL) {
			Intent tutorial = new Intent(OppineBoxActivity.this, TutorialInicialFragmentActivity.class);
			startActivity(tutorial);
		} else {

			// configura a tela
			configuracaoTela = new CCGLSurfaceView(ConfiguracaoPreferencias.CONTEXTO);
			setContentView(configuracaoTela);
			CCDirector.sharedDirector().attachInView(configuracaoTela);

			// configura CCDirector
			CCDirector.sharedDirector().setScreenSize(320, 480);

			if (ConfiguracaoPreferencias.VISUALIZA_SPLASH) {
				// abre tela principal
				CCDirector.sharedDirector().runWithScene(CenarioTelaApresentacao.criaCenario());
			} else {
				CCDirector.sharedDirector().runWithScene(CenarioTelaInicio.criaCenario());
			}

			ConfiguracaoPreferencias.identificaCenaCorrente(1);
			cenarioSair = CenarioTelaSair.criaCenario();
		}
	}

	@Override
	public void onBackPressed() {

		if (ConfiguracaoPreferencias.CENA_CORRENTE == 1) {
			CenarioMenuApresentacao.desabilitaToqueBotoes(false);
		} else if (ConfiguracaoPreferencias.CENA_CORRENTE == 2) {
			CenarioMenuInicio.desabilitaToqueBotoes(false);
		}

		CCDirector.sharedDirector().getRunningScene().addChild(cenarioSair);

	}

}
