package br.com.encoder.componente;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.encoder.ListaEmpresasActivity;
import br.com.encoder.OppineBoxActivity;
import br.com.encoder.SugerirEmpresaActivity;
import br.com.encoder.VideoControleActivity;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoDispositivo;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoFontCaminho;
import br.com.encoder.configuracao.dispositivo.ConfiguracaoPreferencias;
import br.com.oppinebox.R;


/**
 * 
 * 
 * @author Encoder Development
 * Cria um menu reutilizavel para todas as Atividades (Activity)
 * todas as atividades que extende ComponeteMenuContextoFragmentActivity também extende FragmentActivity
 * Todas as atividades que extende ComponeteMenuContextoFragmentActivity mostram o menu na ActionBar
 *
 */
public class ComponeteMenuContextoFragmentActivity extends FragmentActivity{
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 
		super.onCreateOptionsMenu(menu);
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_action_bar, menu);

		MenuItem menuItem = menu.findItem(R.id.menuPesquisar);


		SearchView searchView = (SearchView) menuItem.getActionView();
		searchView.setQueryHint("Pesquisar empresa...");
		
		
		//quando verdadeiro define que o icone não podera ser mudado
		searchView.setIconifiedByDefault(false);
		
		int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
		View searchPlate = searchView.findViewById(searchPlateId);
		
		
		int searchHintIconId = searchView.getContext().getResources().getIdentifier("android:id/search_mag_icon", null, null);
		ImageView searchHintIcon = (ImageView) searchView.findViewById(searchHintIconId);
		searchHintIcon.setImageResource(R.drawable.ic_pesquisar);
		
		
		int closeButtonId = searchView.getContext().getResources().getIdentifier ("android:id/search_close_btn" , null , null );  
		ImageView closeButtonImage = (ImageView) searchView.findViewById (closeButtonId);  
		closeButtonImage.setImageResource (R.drawable.ic_fechar_pesquisa); 
		
		//define que as margens da imagem se ajustem conforme o layout
		closeButtonImage.setAdjustViewBounds(true);
		
		Typeface fontNegrito = Typeface.createFromAsset(this.getAssets(),
				ConfiguracaoFontCaminho.FONT_SENSATION_NEGRITO);
		
		if (searchPlate != null) {
			int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null,
					null);
			TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
			if (searchText != null) {
				searchText.setTypeface(fontNegrito);
				searchText.setTextColor(Color.WHITE);
				searchText.setHintTextColor(Color.WHITE);
			}
		}

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String texto) {
				System.out.println("metodo submit " + texto);

				if (ConfiguracaoDispositivo.existeConexao(ConfiguracaoPreferencias.CONTEXTO)) {

					Intent funcionariosActivity = new Intent(getApplicationContext(),
							ListaEmpresasActivity.class);
					funcionariosActivity.putExtra("nomeEmpresa", texto);
					startActivity(funcionariosActivity);

				} else {
					ComponenteMenssagem.menssagem(
							"OppineBox necessita de uma conexão com a internet para continuar, recomendamos que você se conecte via wifi, por favor verifique sua conexão e tente novamente.",
							Gravity.CENTER_VERTICAL, Toast.LENGTH_LONG, 1);
				}
				return false;

			}

			@Override
			public boolean onQueryTextChange(String texto) {
				// TODO Auto-generated method stub
				System.out.println("metodo change " + texto);
				return false;
			}
		});

		
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.sugeriEmpresa:
			// codigo
			ConfiguracaoPreferencias.vibrarCelular(30);
			Intent a = new Intent(this, SugerirEmpresaActivity.class);
			startActivity(a);
			System.out.println("Sugerir empresa");
			return true;
		case R.id.videoTutorial:
			// codigo
			ConfiguracaoPreferencias.vibrarCelular(30);
			Intent b = new Intent(this, VideoControleActivity.class);
			startActivity(b);
			System.out.println("Video tutorial");
			return true;
		case R.id.sairAplicacao:
			// codigo
			ConfiguracaoPreferencias.vibrarCelular(30);
			
			ConfiguracaoPreferencias.visualizaCenarioSplash(false);
			ConfiguracaoPreferencias.sairMenuPrincipal(true);
			
			Intent c = new Intent(this, OppineBoxActivity.class);
			startActivity(c);
			
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
	
}
