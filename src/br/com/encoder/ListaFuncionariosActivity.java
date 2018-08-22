package br.com.encoder;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.com.encoder.componente.ComponenteImagemRedonda;
import br.com.encoder.componente.ComponenteMenssagem;
import br.com.encoder.componente.ComponeteMenuContextoFragmentActivity;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.lista.adptador.AdaptadorListaFuncionariosEmpresa;
import br.com.encoder.unidade.UnidadeFncionario;
import br.com.encoder.ws.WSCelulaREST;
import br.com.oppinebox.R;

public class ListaFuncionariosActivity extends ComponeteMenuContextoFragmentActivity {
	private List<UnidadeFncionario> funcionarios = new ArrayList<UnidadeFncionario>();
	private ListView listaFuncioanrios;
	private int idEmpresa;
	private Bitmap foto;

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

		if (getIntent().hasExtra("id")) {
			Bundle extras = getIntent().getExtras();
			idEmpresa = extras.getInt("id");
		}

		setContentView(R.layout.lista_layout);
		listaFuncioanrios = (ListView) findViewById(R.id.listViewEfeitoFadeIn);

		if (idEmpresa > 0) {
			CarregaDadosDoWebService t = new CarregaDadosDoWebService(this);
			t.execute("BAIXANDO", "INFORMAÇÕES", "WEB SERVICE");
		} else {
			Intent categoriasActivity = new Intent(ListaFuncionariosActivity.this,
					ListaCategoriasFragmentActivity.class);
			startActivity(categoriasActivity);
		}

		clickRapidoItemLista();

	}

	private void clickRapidoItemLista() {
		listaFuncioanrios.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicaoDaLinha, long id) {
				ConfiguracaoPreferencias.vibrarCelular(30);
				Intent funcionariosActivity = new Intent(ListaFuncionariosActivity.this,
						VotacaoFuncionarioActivity.class);
				UnidadeFncionario u = funcionarios.get(posicaoDaLinha);
				funcionariosActivity.putExtra("idFuncionario", u.getId());
				funcionariosActivity.putExtra("urlFoto", u.getFoto());
				funcionariosActivity.putExtra("nome", u.getNome());
				funcionariosActivity.putExtra("cargo", u.getCargo());
				startActivity(funcionariosActivity);
			}

		});
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

			ComponenteMenssagem.menssagem("Aguarde um instante, estamos trabalhando na sua requisição.",
					Gravity.CLIP_VERTICAL, Toast.LENGTH_LONG, 1);

		}

		@Override
		protected String doInBackground(String... params) {

			WSCelulaREST WSCosumindoServicos = new WSCelulaREST();
			funcionarios = new ArrayList<UnidadeFncionario>();

			try {

				funcionarios = WSCosumindoServicos.getFuncionariosPorEmpresa(idEmpresa);

				if (funcionarios.size() > 0) {

					for (UnidadeFncionario un : funcionarios) {

						if (un.getFoto().length() > 0 & !un.getFoto().isEmpty()) {

							URL url = new URL(un.getFoto());
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

			if (funcionarios != null && funcionarios.size() > 0) {
				AdaptadorListaFuncionariosEmpresa adaptadorFuncionarios = new AdaptadorListaFuncionariosEmpresa(context,
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

			} else {

				ComponenteMenssagem.menssagem("Desculpe, não encontramos nenhum funcionário nesta opção.",
						Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 2);

				Intent categoriasEmpresas = new Intent(ListaFuncionariosActivity.this,
						ListaCategoriasFragmentActivity.class);
				startActivity(categoriasEmpresas);

			}
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
		NavUtils.navigateUpFromSameTask(ListaFuncionariosActivity.this);

	}

}
