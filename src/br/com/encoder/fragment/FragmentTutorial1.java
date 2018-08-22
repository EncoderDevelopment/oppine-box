package br.com.encoder.fragment;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.oppinebox.R;

public class FragmentTutorial1 extends Fragment {

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
	/*	pular = (ImageButton) v.findViewById(R.id.btPular);
		pular.setBackground(getResources().getDrawable(R.drawable.proximo));
		pular.setScaleX(0.3f);
		pular.setScaleY(0.5f);
		pular.setBottom(10);*/

		img.setImageDrawable(getResources().getDrawable(R.drawable.logo));

		titilo.setText("Ola, seja bem vindo!");
		tutorial.setText(
				"Parece que essa é a primeira vez que você utiliza o OppineBox. Esse tutorial lhe ensinará os primeiros passos para você começar a sua avaliação...");
		
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
