package com.example.smorgasbord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class HateMail extends Activity implements View.OnClickListener {

	private EditText email, intro, personName, stupidThing, hatefulDesire, outro;
	private String strEmail, strIntro, strName, strStupidThing, strHatefulDesire, strOutro;
	Button sendEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		initializeViews();
		sendEmail.setOnClickListener(this);
	}

	private void initializeViews() {
		// TODO Auto-generated method stub
		email = (EditText) findViewById(R.id.etEmails);
		intro = (EditText) findViewById(R.id.etIntro);
		personName = (EditText) findViewById(R.id.etName);
		stupidThing = (EditText) findViewById(R.id.etThings);
		hatefulDesire = (EditText) findViewById(R.id.etAction);
		outro = (EditText) findViewById(R.id.etOutro);	
		sendEmail = (Button) findViewById(R.id.bSentEmail);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		extractStringsFromTVs();
		String [] emailAddress = { strEmail };
		String message = "Well hello, " + strName 
				+ ". I just wanted to say " + strIntro
				+ ". I hate you. I hate when you " + strStupidThing
				+ ". Sometimes I just want to " + strHatefulDesire
				+ ". Anyway, that's only a few things I wanted to say and by the way " 
				+ strOutro
				+ ". Be sure to go past parallel next time you squat on leg day." 
				+ "\n" + "P.S. Say hello to your mother for me.";
		
		//intent to send, add extras like the email address, subject, and the message
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddress);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Android powered hatemail");
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		
		startActivity(emailIntent);
		
	}
	
	private void extractStringsFromTVs() {
		// TODO Auto-generated method stub
		strEmail = email.getText().toString();
		strIntro = intro.getText().toString();
		strName = personName.getText().toString();
		strStupidThing = stupidThing.getText().toString();
		strHatefulDesire = hatefulDesire.getText().toString();
		strOutro = outro.getText().toString();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
