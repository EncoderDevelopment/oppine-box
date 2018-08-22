package br.com.encoder;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.encoder.componente.ComponenteMenssagem;
import br.com.encoder.componente.ComponeteMenuContextoFragmentActivity;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTeclado;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.unidade.UnidadeSugestaoEmpresa;
import br.com.encoder.ws.WSCelulaREST;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;
import br.com.oppinebox.R;

/**
 * 
 * @author Encoder Development Class responsavel pela sugestão de novas empresas
 *         que não estão cadastradas no Web Service OppineBox
 */
public class SugerirEmpresaActivity extends ComponeteMenuContextoFragmentActivity {

	private CheckBox comRespostaSim;
	private CheckBox comRespostaNao;

	private ImageButton botaoEnviar;
	private ImageButton botaoCancelar;

	private EditText edtTextNomeEmpresa;
	private EditText edtTextFoneEmpresa;
	private EditText edtTextNomeFuncionario;
	private EditText edtTextComentariosObservacoes;
	private UnidadeSugestaoEmpresa sugestaoEmpresa;

	private ScrollView scroll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// definindo orientação como landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// deixa a tela do dispositivo em modo FULL SCREEN
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		setContentView(R.layout.sugerir_empresa);
		ConfiguracaoTeclado.assistActivity(this);
		
		sugestaoEmpresa = new UnidadeSugestaoEmpresa();
		formataInformacoesNaTela();
		validaCliCkChekbox();
		acoesDosBotoes();

		if (!ConfiguracaoDispositivo.existeConexao(ConfiguracaoPreferencias.CONTEXTO)) {
			ComponenteMenssagem.menssagem(
					"OppineBox necessita de uma conexão com a internet para continuar, recomendamos que você se conecte via wifi, por favor verifique sua conexão e tente novamente.",
					Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 1);
		}

	}

	/**
	 * Responsável por chamar e processar os eventos onClick da Atividade
	 */
	private void acoesDosBotoes() {
		botaoEnviar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (validateFields()) {
					ConfiguracaoPreferencias.vibrarCelular(30);
					EnviaSugestaoEmpresa e = new EnviaSugestaoEmpresa();
					e.execute("ENVIANDO", "INFORMAÇÕES", "EMPERSA SUGERIDA");
				}
			}
		});

		botaoCancelar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// fecha a atividade e retorna para a actividade anterior
				finish();

				// força a atividade a reornar para a atividade pai definida no
				// Manifest.xml
				NavUtils.navigateUpFromSameTask(SugerirEmpresaActivity.this);
			}
		});
	}

	/**
	 * Adiciona os componentes visuais formatados na tela
	 */
	private void formataInformacoesNaTela() {
		Typeface fontNegrito = Typeface.createFromAsset(this.getAssets(),
				ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO);
		Typeface fontNormal = Typeface.createFromAsset(this.getAssets(),
				ConfiguracaoFontCaminho.FONT_SENSATION_REGULAR);

		// Essa classe é chamada quando um objeto está ligado a um editável,
		// seus métodos serão chamados quando o texto for alterado.
		TextWatcher textWatcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			// Este método é chamado para informar que, em algum lugar dentro s
			// , o texto foi alterado
			@Override
			public void afterTextChanged(Editable s) {
				ligaLimpaErros(s);
			}
		};

		edtTextNomeEmpresa = (EditText) findViewById(R.id.editTextNomeEmpresa);
		edtTextNomeEmpresa.addTextChangedListener(textWatcher);

		edtTextFoneEmpresa = (EditText) findViewById(R.id.editTextTelefoneEmpresa);
		edtTextNomeFuncionario = (EditText) findViewById(R.id.editTextNomeFuncionario);
		edtTextComentariosObservacoes = (EditText) findViewById(R.id.editTextComentario);

		TextView textoIntroducao = (TextView) findViewById(R.id.textViewIntroducao);
		TextView textoPergunta = (TextView) findViewById(R.id.textViewPergunta);
		TextView textoNomeEmpresa = (TextView) findViewById(R.id.textViewNomeEmpresa);
		TextView textoFoneEmpresa = (TextView) findViewById(R.id.textViewFoneEmpresa);
		TextView textoNomeFuncionario = (TextView) findViewById(R.id.textViewNomeFuncionario);
		TextView textoComentarios = (TextView) findViewById(R.id.textViewComentario);

		comRespostaSim = (CheckBox) findViewById(R.id.comRespostaSim);
		comRespostaNao = (CheckBox) findViewById(R.id.comRespostaNao);

		botaoEnviar = (ImageButton) findViewById(R.id.btEnviar);
		botaoCancelar = (ImageButton) findViewById(R.id.btCancelar);

		textoIntroducao.setTypeface(fontNegrito);
		textoPergunta.setTypeface(fontNormal);
		textoNomeEmpresa.setTypeface(fontNormal);
		textoFoneEmpresa.setTypeface(fontNormal);
		textoNomeFuncionario.setTypeface(fontNormal);
		textoComentarios.setTypeface(fontNormal);

		comRespostaSim.setTypeface(fontNormal);
		comRespostaNao.setTypeface(fontNormal);

		MaskEditTextChangedListener maskFone = new MaskEditTextChangedListener("(##)#####-####", edtTextFoneEmpresa);
		edtTextFoneEmpresa.addTextChangedListener(maskFone);
		scroll = (ScrollView) findViewById(R.id.ScrollView01sugerir);

		edtTextComentariosObservacoes.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				scroll.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						scroll.scrollTo( botaoCancelar.getLeft() , botaoCancelar.getRight());
						//scroll.fullScroll(View.FOCUS_DOWN);
						
						
					}
				});
			}
		});
		
		edtTextComentariosObservacoes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				scroll.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						scroll.scrollTo( botaoCancelar.getLeft() , botaoCancelar.getRight());
						//scroll.fullScroll(View.FOCUS_DOWN);
						
						
					}
				});
			}
		});


	}

	/**
	 * Verifica qual ChekBox foi clicado e desmarca o que não foi clicado
	 * ChekBox personalizado com layout tipo RadioButton
	 */
	private void validaCliCkChekbox() {

		comRespostaSim.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				comRespostaNao.setChecked(false);
				comRespostaSim.setChecked(true);
				sugestaoEmpresa.setCliente(1);
			}
		});

		comRespostaNao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				comRespostaNao.setChecked(true);
				comRespostaSim.setChecked(false);
				sugestaoEmpresa.setCliente(0);
			}
		});
	}

	/**
	 * Efetua a validação dos campos.Nesse caso, valida se os campos não estão
	 * vazios e se tem tamanho permitido. Nesse método você poderia colocar
	 * outros tipos de validações de acordo com a sua necessidade.
	 *
	 * @return boolean que indica se os campos foram validados com sucesso ou
	 *         não
	 */
	private boolean validateFields() {
		String textoNomeEmpresa = edtTextNomeEmpresa.getText().toString().trim();
		return (!isEmptyFields(textoNomeEmpresa) && validaTamanhoTexto(textoNomeEmpresa));
	}

	private boolean isEmptyFields(String nomeEmpresa) {
		if (TextUtils.isEmpty(nomeEmpresa)) {
			edtTextNomeEmpresa.requestFocus(); // seta o foco para o campo user
			edtTextNomeEmpresa.setError("O nome da empresa é obrigatório");
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param textoEmpresa
	 * @return false caso o tamanho das string seja <=2
	 */
	private boolean validaTamanhoTexto(String textoEmpresa) {

		if (!(textoEmpresa.length() > 2)) {
			edtTextNomeEmpresa.requestFocus();
			edtTextNomeEmpresa.setError("O nome da empresa é muito pequeno");
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param s
	 *            verifica se existe mensagem de erro na tela e chama o método
	 *            para limpar caso exista
	 */
	private void ligaLimpaErros(Editable s) {
		if (!s.toString().isEmpty()) {
			limpaErrosEditText(edtTextNomeEmpresa);
		}
	}

	/**
	 * @param editTexts
	 *            limpa mensagens de erro na tela do android
	 */
	private void limpaErrosEditText(EditText... editTexts) {
		for (EditText editText : editTexts) {
			editText.setError(null);
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		ConfiguracaoPreferencias.vibrarCelular(30);
		super.finish();
	}

	/**
	 * 
	 * @author Encoder Development Sub Class responssável pelo envio das
	 *         informações EnviaSugestaoEmpresa é uma extensão de AsyncTask Por
	 *         padrão qualquer class que necessite de uma conexão I/O deverá
	 *         usar AsyncTask
	 */
	public class EnviaSugestaoEmpresa extends AsyncTask<String, String, String> {

		@SuppressLint("InlinedApi")
		@Override
		protected void onPreExecute() {

			sugestaoEmpresa.setNomeEmpresa(edtTextNomeEmpresa.getText().toString());
			sugestaoEmpresa.setFoneEmpresa(edtTextFoneEmpresa.getText().toString());
			sugestaoEmpresa.setNomeFuncionario(edtTextNomeFuncionario.getText().toString());
			sugestaoEmpresa.setComentariosObservacoes(edtTextComentariosObservacoes.getText().toString());

		}

		@Override
		protected String doInBackground(String... params) {

			WSCelulaREST WSCosumindoServicos = new WSCelulaREST();

			try {

				WSCosumindoServicos.postGravarSugestaoEmpresa(sugestaoEmpresa);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			ComponenteMenssagem.menssagem(
					"Rcebemos sua sugestão ela será analizada e adicionada em nossa base de informações.",
					Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 1);

			// fecha a atividade e retorna para a actividade anterior
			finish();

			// força a atividade a reornar para a atividade pai definida no
			// Manifest.xml
			NavUtils.navigateUpFromSameTask(SugerirEmpresaActivity.this);

		}

	}

}
