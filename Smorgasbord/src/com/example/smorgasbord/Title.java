package com.example.smorgasbord;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Title extends Activity {

	private MediaPlayer openingSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
		openingSong = MediaPlayer.create(Title.this, R.raw.tfwnogf);
		openingSong.start();
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(8000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent start = new Intent("com.example.smorgasbord.MENU");
					startActivity(start);
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
