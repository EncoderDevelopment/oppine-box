package br.com.encoder;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
import br.com.oppinebox.R;

public class VideoControleActivity extends Activity{
	private static final String TAG = "PRANJAL";
	private boolean isImage = false;
	private String reviewImageLink;
	private MediaController mc;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	// definindo orientação como landscape
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    
  	//deixa a tela do dispositivo em modo FULL SCREEN
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		setContentView(R.layout.video_controle);
		VideoView vd = (VideoView) findViewById(R.id.VideoControle);

		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.oppinebox);

		mc = new MediaController(this);
		vd.setMediaController(mc);

		vd.setVideoURI(uri);
		vd.start();
	}
	
	
}
