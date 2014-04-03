package com.example.smorgasbord;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class CaesarCipher extends Activity implements View.OnClickListener{
	
	private EditText message;
	private EditText key;
	private TextView display;
	private Button start;
	private RadioGroup modes;
	private String currentMode = "encrypt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cipher);
		initializeViews();
	}

	private void initializeViews() {
		// TODO Auto-generated method stub
		message = (EditText) findViewById(R.id.etMessage);
		key = (EditText) findViewById(R.id.etKey);
		display = (TextView) findViewById(R.id.tvCipherPrompt);
		start = (Button) findViewById(R.id.bBegin);
		start.setOnClickListener(this);
		modes = (RadioGroup) findViewById(R.id.rgModes);
		modes.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int id) {
				// TODO Auto-generated method stub
				switch(id){
				case R.id.rbEncrypt:
					start.setText("encrypt");
					currentMode = "encrypt";
					break;
				
				case R.id.rbDecrypt:
					start.setText("decrypt");
					currentMode = "decrypt";
					break;
				}
			}
			
		});
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(!inputsValid()){
			return;
		}
		int keyNum = Integer.parseInt(key.getText().toString());
		if(currentMode.equals("decrypt")){
			keyNum = keyNum * -1;
		}
		String messageValue = message.getText().toString();
		translateMessage(messageValue, keyNum);
		
		
	}

	private boolean inputsValid() {
		// TODO Auto-generated method stub
		
		// Displays an error message and aborts translation if the user did not 
		//provide a message to translate and/or an invalid key value was given.
		String errors = "";
		int keyNum = Integer.parseInt(key.getText().toString());
		String messageValue = message.getText().toString();
		if(messageValue.length() == 0){
			errors += "Please input a message. ";
		}
		if((keyNum <1) || keyNum > 26){
			errors += "Please use a valid key value.";
		}
		
		if(errors.length() > 0){
			display.setText(errors);
			return false;
		}
		return true;
		
	}
	
	private void translateMessage(String message, int key) {
		// TODO Auto-generated method stub
		String translation = "";
		char[] msgChars = message.toCharArray();
		for(char c: msgChars){
			//if the character is a letter, we substitute it
			if(Character.isLetter(c)){
				int charOrd = (int)c;
				charOrd += key;
				
				//upper case character
				if(Character.isUpperCase(c)){
					// past the upper bound when adding the key in encryption 
					//must bring back to upper case letters
					if(charOrd > (int)'Z'){
						charOrd -= 26;
					}
					// past the lower bound when subtracting the key in decryption
					//must bring back to upper case letters 
					else if(charOrd < (int)'A'){ 
						charOrd += 26;
					}
				}
				// lower case character
				else{
					if(charOrd > (int)'z'){
						charOrd -= 26;
					}
					else if(charOrd < (int)'a'){ 
						charOrd += 26;
					}
				}
				translation += (char) charOrd;
				
			}
			// if the character is not a letter, just add it normally
			else{
				translation += c;
			}
		}
		//display the translated message
		String result = "Your translated message is:" + "\n" + translation;
		display.setText(result);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();	
		}

	
}
