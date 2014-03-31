package com.example.smorgasbord;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity implements View.OnClickListener{
	
	Button checkCommand;
	ToggleButton passToggle;
	EditText input;
	TextView display;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		
		initializeViews();
		
		passToggle.setOnClickListener(this);
		checkCommand.setOnClickListener(this);
		
	}
	
	private void initializeViews() {
		// TODO Auto-generated method stub
		checkCommand = (Button) findViewById(R.id.buttonResults);
		passToggle = (ToggleButton) findViewById(R.id.tbPassword);
		input = (EditText) findViewById(R.id.etCommands);
		display = (TextView) findViewById(R.id.tvResults);	
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttonResults:
			String command = input.getText().toString();
			
			if(command.contentEquals("left")){
				display.setGravity(Gravity.LEFT);
			}else if(command.contentEquals("center")){
				display.setGravity(Gravity.CENTER);
			}else if(command.contentEquals("right")){
				display.setGravity(Gravity.RIGHT);
			}else if(command.contentEquals("blue")){
				display.setTextColor(Color.BLUE);
			}else if(command.contentEquals("WTF")){
				Random randomVal = new Random();
				display.setText("WTF!!");
				display.setTextSize(randomVal.nextInt(75));
				display.setTextColor(Color.rgb(randomVal.nextInt(256), randomVal.nextInt(256), randomVal.nextInt(256)));
				
				switch(randomVal.nextInt(3)){
					case 0:
						display.setGravity(Gravity.LEFT);
						break;
					case 1:
						display.setGravity(Gravity.CENTER);
						break;
					case 2:
						display.setGravity(Gravity.RIGHT);
					
				}
			}
			else{
				display.setGravity(Gravity.CENTER);
				display.setText("invalid");
				display.setTextSize(20);
				display.setTextColor(Color.BLACK);
			}
			break;
		
		case R.id.tbPassword:
			if(passToggle.isChecked()){
				//toggle is on, password type text
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}else{
				//toggle is off, show the text normally (plain text)
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}
		
	}

}
