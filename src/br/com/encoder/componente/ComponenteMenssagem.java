package br.com.encoder.componente;

import org.cocos2d.nodes.CCDirector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.oppinebox.R;

public class ComponenteMenssagem {

	public static void menssagem(final String messagem , final int gravidade , final int duracao , final int tipoDeMenssagem) {
		
		
		CCDirector.sharedDirector().getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				CCDirector.sharedDirector().getActivity();
				LayoutInflater inflater = (LayoutInflater) CCDirector.sharedDirector().getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				
				View layout = inflater.inflate(R.layout.toast_layout, null);
				TextView tv = (TextView) layout.findViewById(R.id.tvTexto);
				tv.setText(messagem);
				
				LinearLayout llRoot = (LinearLayout) layout.findViewById(R.id.llRoot);
				
				switch (tipoDeMenssagem) {
				case 1:
					llRoot.setBackgroundResource(R.layout.toast_azul);
					break;
				case 2:
					llRoot.setBackgroundResource(R.layout.toast_amarelo);
					break;
				case 3:
					llRoot.setBackgroundResource(R.layout.toast_vermelho);
					break;
				}
				
				
				Toast toast = Toast.makeText(CCDirector.sharedDirector().getActivity().getApplicationContext(), messagem, duracao); 
				toast.setView(layout);
				
				//toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0); toast.show();
				
				toast.setGravity(gravidade, 0, 0); 
				toast.show();	
			}
		});
	}
}
