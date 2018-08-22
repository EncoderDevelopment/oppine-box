package br.com.encoder.fragment;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.nodes.CCDirector;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import br.com.encoder.ListaCategoriasFragmentActivity;
import br.com.encoder.ListaEmpresasActivity;
import br.com.encoder.componente.ComponenteImagemRedonda;
import br.com.encoder.componente.ComponenteMenssagem;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.lista.adptador.AdaptadorGridCategorias;
import br.com.encoder.unidade.UnidadeCategoriaEmpresa;
import br.com.oppinebox.R;

public class FragmentCategorias1 extends Fragment {
	private List<UnidadeCategoriaEmpresa> categoriaEmpresas = new ArrayList<UnidadeCategoriaEmpresa>();
	private GridView listaCategoriasEmpresas;
	private ProgressBar progressBar;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// definindo orienta��o como landscape
		this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// deixa a tela do dispositivo em modo FULL SCREEN
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(15, "Academias", imagemRedonda.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c15), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(35, "Acess�rios", imagemRedonda.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c35), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(49, "Acess�rios para autom�veis", imagemRedonda.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c49), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(23, "A�reo", imagemRedonda.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c23), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(12, "Ag�ncia banc�ria", imagemRedonda.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c12), 180)));
		categoriaEmpresas.add(new UnidadeCategoriaEmpresa(20, "Ag�ncia de turismo", imagemRedonda.getRoundedCornerBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.c20), 180)));
		
		AdaptadorGridCategorias adaptadorEnpresas = new AdaptadorGridCategorias(ConfiguracaoPreferencias.CONTEXTO, categoriaEmpresas);
		listaCategoriasEmpresas.setAdapter(adaptadorEnpresas);
		
		AnimationSet set = new AnimationSet(true);
		Animation anim = AnimationUtils.loadAnimation(ConfiguracaoPreferencias.CONTEXTO, R.anim.slide);
		set.addAnimation(anim);
		LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
		listaCategoriasEmpresas.setLayoutAnimation(controller);
	}

    public static FragmentCategorias1 newInstance(String text) {

    	FragmentCategorias1 f = new FragmentCategorias1();
        Bundle b = new Bundle();
        b.putString("msg", text);

        f.setArguments(b);

        return f;
    }
	
	
}
