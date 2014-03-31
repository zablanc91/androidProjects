package com.example.smorgasbord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Hangman extends Activity implements View.OnClickListener{
	
	/*
	 * The ASCII images representing the number of wrong letter guesses the user has made.
	 * Escape characters had to be used to get the proper image to display.
	 */
	private String[] ascii = {"  +---+\r\n  |   |\r\n      |\r\n      |\r\n      |\r\n      |\r\n=========",
			"  +---+\r\n  |   |\r\n  O   |\r\n      |\r\n      |\r\n      |\r\n=========",
			"  +---+\r\n  |   |\r\n  O   |\r\n  |   |\r\n      |\r\n      |\r\n=========",
			"  +---+\r\n  |   |\r\n  O   |\r\n /|   |\r\n      |\r\n      |\r\n=========",
			"  +---+\r\n  |   |\r\n  O   |\r\n /|\\  |\r\n      |\r\n      |\r\n=========",
			"  +---+\r\n  |   |\r\n  O   |\r\n /|\\  |\r\n /    |\r\n      |\r\n=========",
			"  +---+\r\n  |   |\r\n  O   |\r\n /|\\  |\r\n / \\  |\r\n      |\r\n========="
	};
	
	/*
	 * The string array words holds the potential words that the user has to guess in hangman.
	 */
	private String[] words = {"altruism", "hospice", "draconic", "fauna", "nepotism",
			"lavender", "clandestine", "paradise", "ethereal", "relapse", "southern",
			"archaic", "narwhal", "android", "hazel", "opaque", "maiden", "hedonism",
			"creation", "astral", "crumbling", "freezing"};
	
	
	private EditText guess;
	private Button submitButton;
	private TextView asciiImage;
	private TextView tvWord;
	private TextView display;
	private String mysteryWord = "";
	private String progress = "";
	private int misses;
	private ArrayList<String> alreadyGuessed;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hangman);
		initialize();
		startingState();
	}


	private void initialize() {
		// TODO Auto-generated method stub
		/*
		 * Need to make sure the user can input at most one character per guess.
		 */
		guess = (EditText)findViewById(R.id.etGuess);
		InputFilter[] filterArray = new InputFilter[1];
		filterArray[0] = new InputFilter.LengthFilter(1);
		guess.setFilters(filterArray);
		
		asciiImage = (TextView)findViewById(R.id.tvAscii);
		asciiImage.setText(ascii[misses]);
		display = (TextView)findViewById(R.id.tvDisplay);
		tvWord = (TextView)findViewById(R.id.tvWord);
		submitButton = (Button)findViewById(R.id.buttonSubmit);
		submitButton.setOnClickListener(this);
	}
	
	private void startingState() {
		// TODO Auto-generated method stub
		misses = 0;
		asciiImage.setText(ascii[misses]);
		alreadyGuessed = new ArrayList<String>();
		Random randomVal = new Random();
		mysteryWord = words[randomVal.nextInt(words.length)];
		String blanks = "";
		
		for(int i = 0; i < mysteryWord.length(); i++){
			blanks = blanks.concat("_ ");
		}
		progress = blanks;
		tvWord.setText(progress);
		submitButton.setText(R.string.hangman_submit);
		display.setText(R.string.hangman_guess);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(submitButton.getText().toString().equals("Restart")){
			startingState();
		}	
		else{
			String charGuess = guess.getText().toString();
			if(charGuess.equals("")){
				display.setText("Please input a letter.");
				return;
			}
			else if(alreadyGuessed.contains(charGuess)){
				display.setText("You've already tried that letter.");
				guess.setText("");
				return;
			}
			else{
				if(!mysteryWord.contains(charGuess)){
					display.setText("You guessed wrong.");
					alreadyGuessed.add(charGuess);
					misses++;
					asciiImage.setText(ascii[misses]);
					if(misses == 6){
						tvWord.setText(mysteryWord);
						submitButton.setText("Restart");
					}
					guess.setText("");
				}
				else{
					display.setText("Success!");
					for(int i = 0; i < mysteryWord.length(); i++){
						if(charGuess.equals( mysteryWord.substring(i, i+1))){
							char[] tempProg = progress.toCharArray();
							char[] tempGuess = charGuess.toCharArray();
							tempProg[2*i] = tempGuess[0];
							progress = String.valueOf(tempProg);
						}
					}
					alreadyGuessed.add(charGuess);
					tvWord.setText(progress);
					if(!progress.contains("_")){
						submitButton.setText("Restart");
					}
					guess.setText("");
				}
			
			}
		}
		
		
		
	}	

}
