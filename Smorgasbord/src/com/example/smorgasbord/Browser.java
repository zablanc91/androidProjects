package com.example.smorgasbord;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/*
 * A browser activity for our Smorgasbord app. Consists of a text view
 * for the user to input a valid URL or a search term to be looked up on Bing
 * and a few buttons to navigate and refresh. Javascript is not enabled for now
 * for security purposes.
 */
public class Browser extends Activity implements View.OnClickListener{
	
	Button search, back, forward, refresh, clear;
	EditText searchTerm;
	WebView browser;
	InputMethodManager imm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browser);
		
		initialize();
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		
		browser = (WebView) findViewById(R.id.wvBrowser);
		//enable zoom
		browser.getSettings().setBuiltInZoomControls(true);
	
		//load a default web view
		try{
			browser.loadUrl("http://www.bing.com/");
		} catch(Exception e){
			e.printStackTrace();
		}
		browser.setWebViewClient(new customWebViewClient());
		
		
		search = (Button) findViewById(R.id.bGo);
		back = (Button) findViewById(R.id.bBack);
		forward = (Button) findViewById(R.id.bForward);
		refresh = (Button) findViewById(R.id.bRefresh);
		clear = (Button) findViewById(R.id.bClear);
		searchTerm = (EditText) findViewById(R.id.etSearchTerm);
		
		search.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clear.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getId()){
		//does a search for the web site URL or the term on Bing 
		//and displays it in the Web View
		case(R.id.bGo):
			String website = searchTerm.getText().toString();
			char[] termChar = website.toCharArray();
			// if it's not a URL of the http or https protocol, search on bing;
			if((!isHttp(termChar)) && (!isHttps(termChar))){
				website =  "http://www.bing.com/search?q=" + website;
			}
			browser.loadUrl(website);
			
			//hide the keyboard
			imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(searchTerm.getWindowToken(), 0);
			break;
		
		case(R.id.bBack):
			if(browser.canGoBack()){
				browser.goBack();
			}
			break;
		
		case(R.id.bForward):
			if(browser.canGoForward()){
				browser.goForward();
			}
			break;
		
		case(R.id.bRefresh):
			browser.reload();
			break;
		
		case(R.id.bClear):
			browser.clearHistory();
			break;
		}
		
	}

	private boolean isHttp(char[] temp) {
		// TODO Auto-generated method stub
		int tlen = temp.length;
		//if we can't even check the first 7 chars of the search term to see if 
		//it matches "http://", just search on Bing
		if(tlen < 7){
			return false;
		}
		//if it passes, we need to check the TLD
		if((temp[0] == 'h') && (temp[1] == 't') && (temp[2] == 't') && (temp[3] == 'p') &&
				(temp[4] == ':') && (temp[5] == '/') && (temp[6] == '/') ){
			return checkTLD(temp);
		}
		return false;
	}

	private boolean isHttps(char[] temp) {
		// TODO Auto-generated method stub
		int tlen = temp.length;
		//if we can't even check the first 8 chars of the search term to see if 
		//it matches "https://", just search on Bing
		if(tlen < 8){
			return false;
		}
		//if it passes, we need to check the TLD
		if((temp[0] == 'h') && (temp[1] == 't') && (temp[2] == 't') && (temp[3] == 'p') &&
				(temp[4] == 's') && (temp[5] == ':') && (temp[6] == '/') && (temp[7] == '/')){
			return checkTLD(temp);
		}
		return false;
	}
	
	private boolean checkTLD(char[] temp) {
		// TODO Auto-generated method stub
		//check the web site suffix of the url
		//we only pass top level domains that are .com, .org, .gov, and .net
		int tlen = temp.length;
		
		//smallest url we will accept is "http://www.*.com", which is 16 characters long
		//other wise we just search on Bing
		if(tlen < 16){
			return false;
		}
		if(temp[tlen - 4] == '.'){
			if((temp[tlen -3] == 'c') && (temp[tlen - 2] == 'o') && (temp[tlen -1] == 'm')){
				return true;
			}
			else if((temp[tlen -3] == 'o') && (temp[tlen - 2] == 'r') && (temp[tlen -1] == 'g')){
				return true;
			}
			else if((temp[tlen -3] == 'g') && (temp[tlen - 2] == 'o') && (temp[tlen -1] == 'v')){
				return true;
			}
			else if((temp[tlen -3] == 'n') && (temp[tlen - 2] == 'e') && (temp[tlen -1] == 't')){
				return true;
			}
		}
		return false;
	}

	public class customWebViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}

	}

}
