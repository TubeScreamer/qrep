package cto.lead.betterqapp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//like "Navigate" in WAZE
public class ListWithSearchActivity extends Activity 
{
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private EditText inputSearch;
	private ArrayList<HashMap<String, String>> bandList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_screen);
		
		//ListView data - from concerts I've been :) 
		String[] bands = {"Alestorm", "Heidevolk", "Deep Purple", "Uriah Heep", "Dio", "Aria", "Yngwie", "Kipelov",
				"DDT", "Billy Talent", "GnR", "Ugly Kid Joe", "Ozzy", "Crematorium", "Scorpions"};
		
		this.listView = (ListView)findViewById(R.id.listBands);
		this.inputSearch= (EditText)findViewById(R.id.searchBands);
		
		// add items to listview
		adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.bandName, bands);
		adapter.sort(new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareTo(arg1);
			}
		});
		listView.setAdapter(adapter);
		
		this.listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				CharSequence chosenOne = ((TextView) view).getText();
				Toast.makeText(getApplicationContext(), chosenOne, Toast.LENGTH_SHORT).show();
				inputSearch.setText(chosenOne);
			}
		});
		
		// enable search filter
		inputSearch.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				ListWithSearchActivity.this.adapter.getFilter().filter(cs);
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// nothing 
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// nothing
			}
		});
	}
}
