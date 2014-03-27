/* This app calculates simple interest given the principal amount, interest rate,
 * and number of years obtained from user input.
 */
package com.zablanc91.sicalculator;



import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SICalculatorActivity extends Activity implements OnClickListener, OnSeekBarChangeListener{

	private EditText principalAmount;
	private EditText interest;
	private TextView yearAmount;
	private SeekBar yearSeek;
	private TextView result;
	private Button calculate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sicalculator);
		
		principalAmount = (EditText) findViewById(R.id.amount);
		interest = (EditText) findViewById(R.id.interest);
		yearSeek = (SeekBar) findViewById(R.id.year_seek);
		yearAmount = (TextView) findViewById(R.id.year_amount);
		result = (TextView) findViewById(R.id.result);
		calculate = (Button) findViewById(R.id.calculate);
		
		calculate.setOnClickListener(this);
		yearSeek.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int principal = Integer.parseInt(principalAmount.getText().toString());
		float intRate = Float.parseFloat(interest.getText().toString());
		int numYears = yearSeek.getProgress();
		float SI = calculateSI(principal, numYears, intRate);
		Resources resources = getResources();
		String answer = resources.getString(R.string.end_result, principal, intRate, numYears, SI);	
		result.setText(answer);
		
	}
	/* simple interest = (principal * years * interest rate) / 100
	 */
	private float calculateSI(int principal, int years, float intRate) {
		// TODO Auto-generated method stub
		float SI = (principal * years * intRate)/100;
		return SI;
		
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		if(true){
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub	
		if(true){
		}
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub	
		//update EditText yearAmount here
		int years = arg0.getProgress();
		Resources resources = getResources();
		String seekLabel = resources.getString(R.string.seekbar_label, years);
		yearAmount.setText(seekLabel);
		
	}

}
