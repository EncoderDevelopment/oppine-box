package br.com.encoder;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.com.encoder.componente.ComponenteMenssagem;
import br.com.encoder.componente.ComponeteMenuContextoFragmentActivity;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.lista.adptador.AdaptadorListaEmpresas;
import br.com.encoder.unidade.UnidadeEmpresa;
import br.com.encoder.ws.WSCelulaREST;
import br.com.oppinebox.R;

public class ListaEmpresasActivity extends ComponeteMenuContextoFragmentActivity {
	private List<UnidadeEmpresa> empresas = new ArrayList<UnidadeEmpresa>();
	private ListView listaEmpresas;
	private int idCategoria;
	private String nomeEmpresa;
	private ImageLoader imageLoader;
	private ProgressBar progressBar;
	AdaptadorListaEmpresas adaptadorEnpresas;

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

		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.erro_load_img).showImageOnFail(R.drawable.erro_load_img)
				.showImageOnLoading(R.drawable.loading).cacheInMemory(true).cacheOnDisk(true).build();

		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(ListaEmpresasActivity.this)
				.defaultDisplayImageOptions(displayImageOptions).memoryCacheSize(50 * 1024 * 1024)
				.diskCacheSize(50 * 1024 * 1024).threadPoolSize(5).writeDebugLogs().build();
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(configuration);

		setContentView(R.layout.lista_layout);
		listaEmpresas = (ListView) findViewById(R.id.listViewEfeitoFadeIn);

		Bundle extras = getIntent().getExtras();
		if (getIntent().hasExtra("id"))
			idCategoria = extras.getInt("id");

		nomeEmpresa = new String();
		if (getIntent().hasExtra("nomeEmpresa"))
			nomeEmpresa = extras.getString("nomeEmpresa");

		ConfiguracaoPreferencias.verificaEmpresasEmCache(idCategoria, nomeEmpresa);

		if (idCategoria > 0 || !nomeEmpresa.isEmpty()) {
			if (ConfiguracaoPreferencias.EMPRESAS.size() <= 0) {

				CarregaDadosDoWebService t = new CarregaDadosDoWebService(this);
				t.execute("BAIXANDO", "INFORMAÇÕES", "WEB SERVICE");

			} else {

				progressBar = (ProgressBar) findViewById(R.id.progressbar_loading_listview);
				progressBar.setVisibility(View.GONE);
				adaptadorEnpresas = new AdaptadorListaEmpresas(ListaEmpresasActivity.this,
						ConfiguracaoPreferencias.EMPRESAS, imageLoader);
				listaEmpresas.setAdapter(adaptadorEnpresas);

				/*
				 * AnimationSet set = new AnimationSet(true); Animation anim =
				 * AnimationUtils.loadAnimation(this, R.anim.fade_in);
				 * set.addAnimation(anim); LayoutAnimationController controller
				 * = new LayoutAnimationController(set, 0.5f);
				 * listaEmpresas.setLayoutAnimation(controller);
				 */
			}
		}else{
			
			Intent categoriasActivity = new Intent(ListaEmpresasActivity.this, ListaCategoriasFragmentActivity.class);
			startActivity(categoriasActivity);
		}

		clickRapidoItemLista();

	}

	private void clickRapidoItemLista() {
		listaEmpresas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicaoDaLinha, long id) {
				ConfiguracaoPreferencias.vibrarCelular(30);
				Intent funcionariosActivity = new Intent(ListaEmpresasActivity.this, ListaFuncionariosActivity.class);

				UnidadeEmpresa u;
				if (empresas != null && empresas.size() > 0) {
					u = empresas.get(posicaoDaLinha);
				} else {
					u = ConfiguracaoPreferencias.EMPRESAS.get(posicaoDaLinha);
				}

				funcionariosActivity.putExtra("id", u.getEmpr_id());
				startActivity(funcionariosActivity);
			}

		});
	}

	public class CarregaDadosDoWebService extends AsyncTask<String, String, String> {
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
			empresas = new ArrayList<UnidadeEmpresa>();

			Bundle extras = getIntent().getExtras();

			if (getIntent().hasExtra("id")) {
				try {

					empresas = WSCosumindoServicos.postEmpresasPorIdCategoria(idCategoria);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (getIntent().hasExtra("nomeEmpresa")) {

				try {

					empresas = WSCosumindoServicos.getEmpresasPorNomeEmpresa(nomeEmpresa);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			ConfiguracaoPreferencias.guardaEmpresas(empresas, idCategoria, nomeEmpresa);

			return null;
		}

		@Override
		protected void onPostExecute(String result) {

			progressBar.setVisibility(View.GONE);

			if (empresas != null && empresas.size() > 0) {
				adaptadorEnpresas = new AdaptadorListaEmpresas(ListaEmpresasActivity.this, empresas, imageLoader);
				listaEmpresas.setAdapter(adaptadorEnpresas);

				// cria uma nova animação e adiciona ao listView, animação sera
				// executada na entrada da lista
				/*
				 * AnimationSet set = new AnimationSet(true); Animation anim =
				 * AnimationUtils.loadAnimation(context, R.anim.fade_in);
				 * set.addAnimation(anim); LayoutAnimationController controller
				 * = new LayoutAnimationController(set, 0.5f);
				 * listaEmpresas.setLayoutAnimation(controller);
				 */

			} else {
				// erro.setVisibility(View.VISIBLE);

				ComponenteMenssagem.menssagem("Desculpe, não encontramos nenhuma empresa nessa opção.",
						Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 2);

				Intent sugerirEmpresa = new Intent(ListaEmpresasActivity.this, SugerirEmpresaActivity.class);
				startActivity(sugerirEmpresa);
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
		NavUtils.navigateUpFromSameTask(ListaEmpresasActivity.this);

	}

}
