package cto.lead.betterqapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MapActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// Restore any saved state 
		super.onCreate(savedInstanceState);
		
		// Set content view
		setContentView(R.layout.map_screen);

		// Initialize UI elements
		final EditText addrText = (EditText) findViewById(R.id.location);
		final Button button = (Button) findViewById(R.id.mapButton);

		// Link UI elements to actions in code		
		button.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String address = addrText.getText().toString();
					address = address.replace(' ', '+');
					Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
					startActivity(geoIntent);
				} catch (Exception e) {
					Log.e(MainActivity.TAG, e.toString());
				}
			}
		});
	}
}
