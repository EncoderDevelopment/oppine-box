package br.com.encoder;

import java.util.List;

import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.view.WindowManager;
import br.com.encoder.fragment.FragmentTutorial1;
import br.com.encoder.fragment.FragmentTutorial2;
import br.com.encoder.fragment.FragmentTutorial3;
import br.com.encoder.fragment.FragmentTutorial4;
import br.com.encoder.fragment.FragmentTutorial5;
import br.com.encoder.lista.adptador.AdaptadorFragement;
import br.com.encoder.viewpagerindicator.CirclePageIndicator;
import br.com.encoder.viewpagerindicator.PageIndicator;
import br.com.oppinebox.R;

public class TutorialInicialFragmentActivity extends FragmentActivity {

	PageIndicator mIndicator;
	/** maintains the pager adapter */
	private AdaptadorFragement mPagerAdapter;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.view_page);

		// initialsie the pager
		this.inicialisaPaginacao();
	}
	/**
	 * Initialise the fragments to be paged
	 */
	private void inicialisaPaginacao() {

		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this, new FragmentTutorial1().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentTutorial2().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentTutorial3().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentTutorial4().getClass().getName()));
		fragments.add(Fragment.instantiate(this, new FragmentTutorial5().getClass().getName()));
		this.mPagerAdapter = new AdaptadorFragement(super.getSupportFragmentManager(), fragments);

		ViewPager pager = (ViewPager) super.findViewById(R.id.viewPage);
		pager.setAdapter(this.mPagerAdapter);

		CirclePageIndicator indicator3 = (CirclePageIndicator) findViewById(R.id.circlePageIndicator3);
		mIndicator = indicator3;
		indicator3.setViewPager(pager);
	}

	
}
