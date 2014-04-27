package cto.lead.betterqapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.ImageView;
import android.widget.RelativeLayout;


@SuppressWarnings("deprecation")
public class ClockActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock_screen);
		
		AnalogClock ac = (AnalogClock)findViewById(R.id.analogClock1);
		ac.setBackgroundColor(Color.YELLOW);
		ac.setSoundEffectsEnabled(true);
		
		DigitalClock dc = (DigitalClock)findViewById(R.id.digitalClock1);
		
		dc.setBackgroundColor(Color.MAGENTA);
		
		/*
		 * display picture 
		 */
		RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.frame);
		ImageView bubbleView = new ImageView(getApplicationContext());
		bubbleView.setImageDrawable(getResources().getDrawable(R.drawable.b128));

		int width = (int) getResources().getDimension(R.dimen.image_width);
		int height = (int) getResources().getDimension(R.dimen.image_height);

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);

		bubbleView.setLayoutParams(params);
		relativeLayout.addView(bubbleView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
