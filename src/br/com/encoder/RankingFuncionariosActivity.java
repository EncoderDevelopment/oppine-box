package br.com.encoder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import br.com.encoder.componente.ComponenteImagemRedonda;
import br.com.encoder.componente.ComponeteMenuContextoFragmentActivity;
import br.com.encoder.lista.adptador.AdaptadorRankingFuncionarios;
import br.com.encoder.unidade.UnidadeFncionarioRanking;
import br.com.encoder.ws.WSCelulaREST;
import br.com.oppinebox.R;

public class RankingFuncionariosActivity extends ComponeteMenuContextoFragmentActivity {
	private List<UnidadeFncionarioRanking> funcionarios;
	private ListView listaFuncioanrios;
	private Bitmap foto;
	private int idFuncionario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// definindo orientação como landscape
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// deixa a tela do dispositivo em modo FULL SCREEN
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		setContentView(R.layout.lista_layout);
		listaFuncioanrios = (ListView) findViewById(R.id.listViewEfeitoFadeIn);

		Bundle extras = getIntent().getExtras();
		idFuncionario = extras.getInt("idFuncionario");

		CarregaDadosDoWebService t = new CarregaDadosDoWebService(this);
		t.execute("BAIXANDO", "INFORMAÇÕES", "WEB SERVICE");

	}

	public class CarregaDadosDoWebService extends AsyncTask<String, String, String> {
		private ProgressBar progressBar;
		private Context context;

		public CarregaDadosDoWebService(Context context) {
			this.context = context;
		}

		@SuppressLint("InlinedApi")
		@Override
		protected void onPreExecute() {
			progressBar = (ProgressBar) findViewById(R.id.progressbar_loading_listview);
			progressBar.setVisibility(View.VISIBLE);

		}

		@SuppressWarnings("unchecked")
		@Override
		protected String doInBackground(String... params) {

			WSCelulaREST WSCosumindoServicos = new WSCelulaREST();
			funcionarios = new ArrayList<UnidadeFncionarioRanking>();

			try {

				funcionarios = WSCosumindoServicos.getRankingPorIdFuncionario(idFuncionario);

				if (funcionarios != null && funcionarios.size() > 0) {

					for (UnidadeFncionarioRanking un : funcionarios) {

						if (un.getFunc_foto().length() > 0 & !un.getFunc_foto().isEmpty()) {

							URL url = new URL(un.getFunc_foto());
							HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
							conexao.setRequestMethod("GET");
							conexao.setDoInput(true);
							conexao.connect();
							InputStream is = conexao.getInputStream();

							foto = BitmapFactory.decodeStream(is);
							ComponenteImagemRedonda imagemRedonda = new ComponenteImagemRedonda();
							un.setFotoFuncionario(imagemRedonda.getRoundedCornerBitmap(foto, 10));
						}
					}
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

			AdaptadorRankingFuncionarios adaptadorFuncionarios = new AdaptadorRankingFuncionarios(context,
					funcionarios);
			listaFuncioanrios.setAdapter(adaptadorFuncionarios);

			// cria uma nova animação e adiciona ao listView, animação sera
			// executada na entrada da lista
			AnimationSet set = new AnimationSet(true);
			Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);
			set.addAnimation(anim);
			LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
			listaFuncioanrios.setLayoutAnimation(controller);
			listaFuncioanrios.setBackgroundColor(color.white);

		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		// fecha a atividade e retorna para a actividade anterior
		finish();

		// força a atividade a reornar para a atividade pai definida no
		// Manifest.xml
		NavUtils.navigateUpFromSameTask(RankingFuncionariosActivity.this);
	}

}
