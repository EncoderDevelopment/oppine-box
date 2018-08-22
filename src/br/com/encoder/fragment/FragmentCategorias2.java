package br.com.encoder.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import br.com.encoder.ListaEmpresasActivity;
import br.com.encoder.componente.ComponenteImagemRedonda;
import br.com.encoder.componente.ComponenteMenssagem;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.lista.adptador.AdaptadorGridCategorias;
import br.com.encoder.unidade.UnidadeCategoriaEmpresa;
import br.com.oppinebox.R;

public class FragmentCategorias2 extends Fragment {
	private List<UnidadeCategoriaEmpresa> categoriaEmpresas = new ArrayList<UnidadeCategoriaEmpresa>();
	private GridView listaCategoriasEmpresas;
	private ProgressBar progressBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// definindo orienta��o como landscape
		this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// deixa a tela do dispositivo em modo FULL SCREEN
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		View v = inflater.inflate(R.layout.categoria_layout, container, false);
		listaCategoriasEmpresas = (GridView) v.findViewById(R.id.gridview);

		preencheListaCategoriasFixa();		
		
		 progressBar = (ProgressBar) v.findViewById(R.id.progressbar_loading);
		 progressBar.setVisibility(View.GONE);
		
		return v;
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		clickRapidoItemLista();
	}

	private void clickRapidoItemLista() {
		listaCategoriasEmpresas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicaoDaLinha, long id) {
				ConfiguracaoPreferencias.vibrarCelular(30);

				if (ConfiguracaoDispositivo.existeConexao(ConfiguracaoPreferencias.CONTEXTO)) {

					Intent funcionariosActivity = new Intent(getActivity(), ListaEmpresasActivity.class);
					UnidadeCategoriaEmpresa u = categoriaEmpresas.get(posicaoDaLinha);
					funcionariosActivity.putExtra("id", u.getCate_id());
					startActivity(funcionariosActivity);

				} else {
					ComponenteMenssagem.menssagem(
							"OppineBox necessita de uma conex�o com a internet para continuar, recomendamos que voc� se conecte via wifi, por favor verifique sua conex�o e tente novamente.",
							Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 2);
				}
			}

		});
	}


	public void preencheListaCategoriasFixa() {

		categoriaEmpresas = new ArrayList<UnidadeCategoriaEmpresa>();
		ComponenteImagemRedonda imagemRedonda = new ComponenteImagemRedonda();

		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(4, "Auto pe�as", imagemRedonda
				.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c4), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(7, "Bar", imagemRedonda
				.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c7), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(28, "Bebidas", imagemRedonda
				.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c28), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(43, "Beleza e est�tica", imagemRedonda
				.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c43), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(46, "Brinquedos", imagemRedonda
				.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c46), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(33, "Cal�ados", imagemRedonda
				.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c33), 180)));
		
		AdaptadorGridCategorias adaptadorEnpresas = new AdaptadorGridCategorias(ConfiguracaoPreferencias.CONTEXTO, categoriaEmpresas);
		listaCategoriasEmpresas.setAdapter(adaptadorEnpresas);
		
		AnimationSet set = new AnimationSet(true);
		Animation anim = AnimationUtils.loadAnimation(ConfiguracaoPreferencias.CONTEXTO, R.anim.slide);
		set.addAnimation(anim);
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
		listaCategoriasEmpresas.setLayoutAnimation(controller);
	}

	public static FragmentCategorias2 newInstance(String text) {

		FragmentCategorias2 f = new FragmentCategorias2();
		Bundle b = new Bundle();
		b.putString("msg", text);

		f.setArguments(b);

		return f;
	}

}
