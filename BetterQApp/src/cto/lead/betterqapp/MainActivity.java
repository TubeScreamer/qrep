package cto.lead.betterqapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
 * ---------------------Login logic------------------------
 * 0. Register 
 * 	- new user enters name and password, that saved in DB (Server)
 * 1. Login 
 * 	- first time the user enters his name and password 
 * 	- the app remembers his credentials (phone shared preferences) 
 * 2. Next time the login window is skipped (the app takes all data from phone shared preferences)
 * 3. No log out option (like WAZE, Whatsapp,...), only one account per phone
 */
public class MainActivity extends Activity {
	private static final int MAX_ATTEMPTS = 3;
	public static final String TAG = "BetterQApp";
	private static final String USER_IS_LOGGED = "logged_flag";
	private static final String LOGGED_USER_NAME = "logged_user_name";
	
	private EditText username;
	private EditText password;
	private Button login;
	private int counter;
	private SharedPreferences prefs;
	
	private static final HashMap<String, String> userCredentials = new HashMap<String, String>() { {
			put("nik", "zu");
			put("alex", "du");
			put("admin", "admin");
			put("zero", "hero");
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		prefs  = getPreferences(MODE_PRIVATE);
		if(!prefs.getBoolean(USER_IS_LOGGED, false)){ // second parameter?
			setContentView(R.layout.login_screen);
			initLoginData();
		}
		else{
			setContentView(R.layout.main_screen);
			showGreeting();
		}
	}

	private void initLoginData(){
		counter = MAX_ATTEMPTS;
		username = (EditText)findViewById(R.id.etUsername);
		password = (EditText)findViewById(R.id.etPassword);
		login = (Button)findViewById(R.id.btnLogin);
	}
	
	public void register(View view){
		// check if the user is already exists
		if(userExists()){
			Toast.makeText(getApplicationContext(), "User Already Exists!", Toast.LENGTH_SHORT).show();
		}
		else {
			// Register new user
			// for now we'll use hashmap to store default users
			// TODO call Server method to update DB - register
			// for now, there is nothing to do
		}
	}
	
	private void showGreeting(){
		TextView greeting = (TextView)findViewById(R.id.tvLoggedUser);
		String loggedUser = prefs.getString(LOGGED_USER_NAME, "");
		greeting.setText("Hello " + loggedUser + "!");
	}
	
	private boolean userExists(){
		// TODO make call to the Server instead of the code below
		if(userCredentials.containsKey(username.getText().toString())){
			return true;
		}
		return false;
	}
	
	private boolean isLegalUser(){
		// TODO make call to the Server instead of the code below
		if(userExists() && userCredentials.get(username.getText().toString()).equals(password.getText().toString())){
			return true;
		}
		return false;
	}
	
	public void login(View view){	
		if(isLegalUser()){
			Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
			// store in shared preferences
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean(USER_IS_LOGGED, true);
			editor.putString(LOGGED_USER_NAME, username.getText().toString());
			editor.commit();
						
			this.setContentView(R.layout.main_screen);
			showGreeting();
		}
		else{
			Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
			counter--;
			if(counter <= 0){
				login.setEnabled(false);
			}
		}
	}
	
	public void showClock(View view){
		Intent intent = new Intent(this, ClockActivity.class);
		startActivity(intent);
	}
	
	public void showMap(View view){
		Intent intent = new Intent(this, MapActivity.class);
		startActivity(intent);
	}
	
	public void showList(View view){
		Intent intent = new Intent(this, ListWithSearchActivity.class);
		startActivity(intent);
	}
	
	public void showUIElements(View view){
		Intent intent = new Intent(this, UIElementsActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onRestart() {
		Log.i(TAG, "The activity is about to be restarted.");
		super.onRestart();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "The activity is about to become visible.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "The activity has become visible (it is now \"resumed\")");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG,
				"Another activity is taking focus (this activity is about to be \"paused\")");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "The activity is no longer visible (it is now \"stopped\")");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "The activity is about to be destroyed.");
	}
}
