package br.com.encoder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import br.com.encoder.componente.ComponenteImagemRedonda;
import br.com.encoder.componente.ComponenteMenssagem;
import br.com.encoder.componente.ComponeteMenuContextoFragmentActivity;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoTeclado;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.unidade.UnidadeAvaliacao;
import br.com.encoder.ws.WSCelulaREST;
import br.com.oppinebox.R;

public class VotacaoFuncionarioActivity extends ComponeteMenuContextoFragmentActivity {
	private int idFuncionario;
	private String nome;
	private String urlFoto;
	private String cargo;
	private ImageView foto;
	private RatingBar ratingBar;
	private UnidadeAvaliacao avaliacao;

	private CheckBox comSorriso;
	private CheckBox comAtencao;
	private CheckBox comEducacao;
	private CheckBox comEmpatia;
	private CheckBox comInformacoesCorretas;
	private CheckBox comPontualidade;
	private CheckBox comRapidez;
	private CheckBox comRespeito;

	private ImageButton btAvaliar;
	private ImageButton btCancelar;
	private EditText editTextComentario;
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
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		setContentView(R.layout.votacao_funcionario);
		ConfiguracaoTeclado.assistActivity(this);

		avaliacao = new UnidadeAvaliacao();
		formataInformacoesNaTela();

		if (idFuncionario > 0) {
			CarregaDadosDoWebService t = new CarregaDadosDoWebService();
			t.execute("BAIXANDO", "INFORMAÇÕES", "WEB SERVICE");
		}else{
			Intent categoriasActivity = new Intent(VotacaoFuncionarioActivity.this, ListaCategoriasFragmentActivity.class);
			startActivity(categoriasActivity);
		}
		
		avaliarAtendimento();

	}

	private void avaliarAtendimento() {

		btAvaliar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				validaCliCkChekbox();

				if (validarAvaliacao(getAvaliacao())) {

					EnviarDadosVotaçãoWebService t = new EnviarDadosVotaçãoWebService();
					t.execute("ENVIAR", "VOTAÇÃO", "WEB SERVICE");

				} else {
					ComponenteMenssagem.menssagem(
							"Para completar a avaliação você deve marcar ao menos um item do atendimento.",
							Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 2);
				}

			}
		});

		btCancelar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float avaliacao, boolean fromUser) {
				getAvaliacao().setNota(new Integer((int) avaliacao));
			}
		});
	}

	private void formataInformacoesNaTela() {

		btAvaliar = (ImageButton) findViewById(R.id.btAvaliar);
		btCancelar = (ImageButton) findViewById(R.id.btCancelar);

		Bundle extras = getIntent().getExtras();

		idFuncionario = extras.getInt("idFuncionario");
		nome = extras.getString("nome");
		cargo = extras.getString("cargo");
		urlFoto = extras.getString("urlFoto");

		getAvaliacao().setIdFuncionario(idFuncionario);

		Typeface font = Typeface.createFromAsset(this.getAssets(), ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO);
		TextView nomeFuncionario = (TextView) findViewById(R.id.txNomeFuncionario);
		nomeFuncionario.setText(nome);
		nomeFuncionario.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView cargoFuncionario = (TextView) findViewById(R.id.txCargoFuncionario);
		cargoFuncionario.setText(cargo);
		cargoFuncionario.setGravity(Gravity.CENTER_HORIZONTAL);

		TextView avaliacao = (TextView) findViewById(R.id.txAvaliacao);
		TextView nota = (TextView) findViewById(R.id.txNota);

		TextView textoComentarios = (TextView) findViewById(R.id.textViewComentario);
		textoComentarios.setGravity(Gravity.CENTER_HORIZONTAL);

		nota.setTypeface(font);
		avaliacao.setTypeface(font);
		nomeFuncionario.setTypeface(font);
		cargoFuncionario.setTypeface(font);
		textoComentarios.setTypeface(font);

		comSorriso = (CheckBox) findViewById(R.id.comSorriso);
		comAtencao = (CheckBox) findViewById(R.id.comAtencao);
		comEducacao = (CheckBox) findViewById(R.id.comEducacao);
		comEmpatia = (CheckBox) findViewById(R.id.comEmpatia);
		comInformacoesCorretas = (CheckBox) findViewById(R.id.comInformacoesCorretas);
		comPontualidade = (CheckBox) findViewById(R.id.comPontualidade);
		comRapidez = (CheckBox) findViewById(R.id.comRapidez);
		comRespeito = (CheckBox) findViewById(R.id.comRespeito);

		comRespeito.setTypeface(font);
		comRapidez.setTypeface(font);
		comPontualidade.setTypeface(font);
		comInformacoesCorretas.setTypeface(font);
		comEmpatia.setTypeface(font);
		comEducacao.setTypeface(font);
		comSorriso.setTypeface(font);
		comAtencao.setTypeface(font);

		editTextComentario = (EditText) findViewById(R.id.editTextComentario);

		ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		ratingBar.setNumStars(5);
		scroll = (ScrollView) findViewById(R.id.ScrollView01votacao);
		
		editTextComentario.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				scroll.post(new Runnable() {

					@Override
					public void run() {
						scroll.scrollTo( btCancelar.getLeft() , btCancelar.getRight());
						scroll.fullScroll(View.FOCUS_DOWN);
						
						
					}
				});
				
			}
		});
		
		editTextComentario.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scroll.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						scroll.scrollTo( btCancelar.getLeft() , btCancelar.getRight());
						scroll.fullScroll(View.FOCUS_DOWN);
						
						
					}
				});
				
			}
		});
	}
	

	private void validaCliCkChekbox() {
		if (comSorriso.isChecked()) {
			getAvaliacao().setComSorriso(1);
		} else {
			getAvaliacao().setComSorriso(0);
		}

		if (comAtencao.isChecked()) {
			getAvaliacao().setComAtencao(1);
		} else {
			getAvaliacao().setComAtencao(0);
		}

		if (comEducacao.isChecked()) {
			getAvaliacao().setComEducacao(1);
		} else {
			getAvaliacao().setComEducacao(0);
		}

		if (comEmpatia.isChecked()) {
			getAvaliacao().setComEmpatia(1);
		} else {
			getAvaliacao().setComEmpatia(0);
		}

		if (comInformacoesCorretas.isChecked()) {
			getAvaliacao().setComInformacoesCorretas(1);
		} else {
			getAvaliacao().setComInformacoesCorretas(0);
		}

		if (comPontualidade.isChecked()) {
			getAvaliacao().setComPontualidade(1);
		} else {
			getAvaliacao().setComPontualidade(0);
		}

		if (comRapidez.isChecked()) {
			getAvaliacao().setComRapidez(1);
		} else {
			getAvaliacao().setComRapidez(0);
		}

		if (comRespeito.isChecked()) {
			getAvaliacao().setComRespeito(1);
		} else {
			getAvaliacao().setComRespeito(0);
		}
	}

	public class CarregaDadosDoWebService extends AsyncTask<String, String, String> {

		private Bitmap icone;
		private ProgressBar progressBar;

		@Override
		protected void onPreExecute() {
			progressBar = (ProgressBar) findViewById(R.id.progressbar_loading_votacao);
			progressBar.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(String... params) {

			try {

				if (!urlFoto.isEmpty()) {

					URL url = new URL(urlFoto);
					HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
					conexao.setRequestMethod("GET");
					conexao.setDoInput(true);
					conexao.connect();
					InputStream is = conexao.getInputStream();

					icone = BitmapFactory.decodeStream(is);
					ComponenteImagemRedonda imagemRedonda = new ComponenteImagemRedonda();

					foto = (ImageView) findViewById(R.id.fotoVotacao);
					foto.setImageBitmap(imagemRedonda.getRoundedCornerBitmap(icone, 10));
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			progressBar.setVisibility(View.GONE);

		}

	}

	public class EnviarDadosVotaçãoWebService extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// System.out.println("Nota: " + getAvaliacao().getNota());
			avaliacao.setComentario(editTextComentario.getText().toString());
		}

		@Override
		protected String doInBackground(String... params) {

			WSCelulaREST WSCosumindoServicos = new WSCelulaREST();
			try {
				WSCosumindoServicos.getGravarAvaliacao(avaliacao);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			ComponenteMenssagem.menssagem("Agradecemos a sua avaliação, recomende OppineBox para os seus amigos.",
					Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 1);
			Intent votacaoActivity = new Intent(VotacaoFuncionarioActivity.this, RankingFuncionariosActivity.class);
			votacaoActivity.putExtra("idFuncionario", idFuncionario);
			startActivity(votacaoActivity);

			/*
			 * for (UnidadeFncionarioRanking f : funcionarios) {
			 * System.out.println("Nome " + f.getFunc_nome());
			 * System.out.println("Ranking " + f.getPorcentagem()); }
			 */

		}

	}

	public UnidadeAvaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(UnidadeAvaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	private boolean validarAvaliacao(UnidadeAvaliacao avaliacao) {
		if (avaliacao.getComAtencao() == 0 && avaliacao.getComEducacao() == 0 && avaliacao.getComEmpatia() == 0
				&& avaliacao.getComInformacoesCorretas() == 0 && avaliacao.getComPontualidade() == 0
				&& avaliacao.getComRapidez() == 0 && avaliacao.getComRespeito() == 0 && avaliacao.getComSorriso() == 0)
			return false;
		return true;

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();

		// força a atividade a reornar para a atividade pai definida no
		// Manifest.xml
		NavUtils.navigateUpFromSameTask(VotacaoFuncionarioActivity.this);
	}

}