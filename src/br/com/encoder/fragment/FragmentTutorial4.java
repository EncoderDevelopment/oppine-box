package br.com.encoder.fragment;

import java.util.ArrayList;
import java.util.List;

import org.cocos2d.nodes.CCDirector;
import android.annotation.SuppressLint;
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
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import br.com.encoder.ListaCategoriasFragmentActivity;
import br.com.encoder.ListaEmpresasActivity;
import br.com.encoder.OppineBoxActivity;
import br.com.encoder.componente.ComponenteImagemRedonda;
import br.com.encoder.componente.ComponenteMenssagem;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.encoder.lista.adptador.AdaptadorGridCategorias;
import br.com.encoder.unidade.UnidadeCategoriaEmpresa;
import br.com.oppinebox.R;

public class FragmentTutorial4 extends Fragment {
	private ImageView img;
	private TextView titilo;
	private TextView tutorial;
	private ImageButton pular;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// definindo orientação como landscape
		this.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// deixa a tela do dispositivo em modo FULL SCREEN
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		View v = inflater.inflate(R.layout.tutorial_layout, container, false);

		criaCompontes(v);

		return v;
	}

	@SuppressLint("NewApi")
	private void criaCompontes(View v) {
		img = (ImageView) v.findViewById(R.id.tutorialImagem);
		titilo = (TextView) v.findViewById(R.id.tutorialTitulo);
		tutorial = (TextView) v.findViewById(R.id.tutorialTutorial);
		/*pular = (ImageButton) v.findViewById(R.id.btPular);
		pular.setBackground(getResources().getDrawable(R.drawable.proximo));
		pular.setScaleX(0.3f);
		pular.setScaleY(0.5f);
		pular.setBottom(10);*/

		img.setImageDrawable(getResources().getDrawable(R.drawable.passo3));

		titilo.setText("Terceiro passo!");
		tutorial.setText(
				"Selecione o funcionário para iniciar a valiação, após a finalização você concorre a prêmios e descontos exclusivos...");

		/*pular.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ConfiguracaoPreferencias.salvaPreferencias();

				ConfiguracaoPreferencias.vibrarCelular(30);
				Intent inicio = new Intent(getActivity(), OppineBoxActivity.class);
				startActivity(inicio);
			}
		});*/

	}

}
