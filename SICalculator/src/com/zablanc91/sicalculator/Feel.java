/* This code comprises the opening intro for the SI Calculator app.
 * Shows a nice little image and plays a short tune for few seconds before
 * starting the SI Calculator activity.
 */


package com.zablanc91.sicalculator;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Feel extends Activity{

	private MediaPlayer openingSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.titlescreen);
		openingSong = MediaPlayer.create(Feel.this, R.raw.tfwnogf);
		openingSong.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(8000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent siStart = new Intent("com.zablanc91.sicalculator.SICALCULATORACTIVITY");
					startActivity(siStart);
				}
			}
		};
		
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		openingSong.release();
		finish();
	}
	
	

}
