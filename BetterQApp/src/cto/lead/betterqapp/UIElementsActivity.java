package cto.lead.betterqapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

/*
 *  check boxes				// UICheckBox				V
 *  combo boxes 			// UISpinner 				V
 *  radio buttons			// UIRadioGroup				V
 *  rate					// UIRatingsBar				V
 */
public class UIElementsActivity extends Activity 
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_elements_screen);
		
		/*
		 * Check box
		 */
		final CheckBox checkbox = (CheckBox) findViewById(R.id.ui_checkbox);
        checkbox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkbox.isChecked()) {
					checkbox.setText("I'm checked");
				} else {
					checkbox.setText("I'm not checked");
				}
			}
		});
        
        final Button button = (Button) findViewById(R.id.ui_button);
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (checkbox.isShown()) {
					checkbox.setVisibility(View.INVISIBLE);
					button.setText("Unhide CheckBox");
				} else {
					checkbox.setVisibility(View.VISIBLE);
					button.setText("Hide CheckBox");
				}
			}
		});
        
        /*
         * Radio
         */
        final TextView tv = (TextView) findViewById(R.id.ui_textView);

		final OnClickListener radioListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				RadioButton rb = (RadioButton) v;
				String answer = "";
				if(rb.getText().toString().toLowerCase().contains("army")){
					answer = "Don't be so stupid!";
				}
				else if(rb.getText().toString().toLowerCase().contains("whatever")) {
					answer = "You suck!";
				}
				else{
					answer = "Right answer, bro!";
				}
				tv.setText(answer);
			}
		};
		final RadioButton choice1 = (RadioButton) findViewById(R.id.choice1);
		choice1.setOnClickListener(radioListener);

		final RadioButton choice2 = (RadioButton) findViewById(R.id.choice2);
		choice2.setOnClickListener(radioListener);

		final RadioButton choice3 = (RadioButton) findViewById(R.id.choice3);
		choice3.setOnClickListener(radioListener);
		
		/*
		 * rating bar
		 */
		
		final TextView tv2 = (TextView) findViewById(R.id.ui_textView2);
        final RatingBar bar = (RatingBar) findViewById(R.id.ratingbar);
        
        bar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
				tv2.setText("Rating: " + rating);
			}
		});
        
        /*
         * Spinner
         */
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.visited_capitols, 
				R.layout.dropdown_item);
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
				Toast.makeText(parent.getContext(), "The city is " + parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG)
					.show();
			}

			public void onNothingSelected(AdapterView<?> parent) {
				// nothing
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
