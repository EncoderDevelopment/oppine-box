package br.com.encoder;

import java.util.List;

import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import br.com.encoder.componente.ComponeteMenuContextoFragmentActivity;
import br.com.encoder.fragment.FragmentCategorias1;
import br.com.encoder.fragment.FragmentCategorias2;
import br.com.encoder.fragment.FragmentCategorias3;
import br.com.encoder.fragment.FragmentCategorias4;
import br.com.encoder.fragment.FragmentCategorias5;
import br.com.encoder.fragment.FragmentCategorias6;
import br.com.encoder.fragment.FragmentCategorias7;
import br.com.encoder.fragment.FragmentCategorias8;
import br.com.encoder.lista.adptador.AdaptadorFragement;
import br.com.encoder.viewpagerindicator.CirclePageIndicator;
import br.com.encoder.viewpagerindicator.PageIndicator;
import br.com.oppinebox.R;

public class ListaCategoriasFragmentActivity extends ComponeteMenuContextoFragmentActivity {

	PageIndicator mIndicator;
	/** maintains the pager adapter */
	private AdaptadorFragement mPagerAdapter;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		super.setContentView(R.layout.view_page);

		/*
		 * adiciona tirulo e subtitulo na activity ActionBar actionBar =
		 * getActionBar();
		 * actionBar.setSubtitle("https://zarelli.wordpress.com");
		 * actionBar.setTitle("OppineBox");
		 */

		// initialsie the pager
		this.inicialisaPaginacao();

	}

	/**
	 * Initialise the fragments to be paged
	 */
	private void inicialisaPaginacao() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, new FragmentCategorias1().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentCategorias2().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentCategorias3().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentCategorias4().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentCategorias5().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentCategorias6().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentCategorias7().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentCategorias8().getClass().getName()));
		this.mPagerAdapter = new AdaptadorFragement(super.getSupportFragmentManager(), fragments);

		ViewPager pager = (ViewPager) super.findViewById(R.id.viewPage);
		pager.setAdapter(this.mPagerAdapter);

		CirclePageIndicator indicator3 = (CirclePageIndicator) findViewById(R.id.circlePageIndicator3);
		mIndicator = indicator3;
		indicator3.setViewPager(pager);
	}

	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		// fecha a atividade e retorna para a actividade anterior
		finish();

		// força a atividade a reornar para a atividade pai definida no
		// Manifest.xml
		NavUtils.navigateUpFromSameTask(ListaCategoriasFragmentActivity.this);
		
	}

}
