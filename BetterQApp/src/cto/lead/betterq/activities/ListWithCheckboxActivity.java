package cto.lead.betterq.activities;

import java.util.ArrayList;

import cto.lead.betterq.R;
import cto.lead.betterq.R.id;
import cto.lead.betterq.R.layout;
import cto.lead.betterq.uientities.PlaceUI;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class ListWithCheckboxActivity extends Activity {

	 MyCustomAdapter dataAdapter = null;

	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_list_with_checkbox);

	  // Generate list View from ArrayList
	  displayListView();

	  checkButtonClick();

	 }

	 private void displayListView() {

	  // Array list of countries
	  ArrayList<PlaceUI> stateList = new ArrayList<PlaceUI>();

	  PlaceUI place = new PlaceUI("1", "India", false);
	  stateList.add(place);
	  place = new PlaceUI("2", "Australia", true);
	  stateList.add(place);
	  place = new PlaceUI("3", "Brazil", false);
	  stateList.add(place);
	  place = new PlaceUI("4", "China", true);
	  stateList.add(place);
	  place = new PlaceUI("5", "Germany", true);
	  stateList.add(place);
	  place = new PlaceUI("6", "Hungary", false);
	  stateList.add(place);
	  place = new PlaceUI("7", "Italy", false);
	  stateList.add(place);
	  place = new PlaceUI("8", "US", false);
	  stateList.add(place);
	  place = new PlaceUI("9", "UK", false);
	  stateList.add(place);

	  // create an ArrayAdaptar from the String Array
	  dataAdapter = new MyCustomAdapter(this, R.layout.state_info, stateList);
	  ListView listView = (ListView) findViewById(R.id.listView1);
	  // Assign adapter to ListView
	  listView.setAdapter(dataAdapter);

	  listView.setOnItemClickListener(new OnItemClickListener() {

	   public void onItemClick(AdapterView<?> parent, View view,
	     int position, long id) {
	    // When clicked, show a toast with the TextView text
	    PlaceUI state = (PlaceUI) parent.getItemAtPosition(position);
	    Toast.makeText(getApplicationContext(),
	      "Clicked on : " + state.getName(), Toast.LENGTH_LONG)
	      .show();
	   }
	  });
	 }

	 private class MyCustomAdapter extends ArrayAdapter<PlaceUI> {

	  private ArrayList<PlaceUI> stateList;

	  public MyCustomAdapter(Context context, int textViewResourceId,

	  ArrayList<PlaceUI> stateList) {
	   super(context, textViewResourceId, stateList);
	   this.stateList = new ArrayList<PlaceUI>();
	   this.stateList.addAll(stateList);
	  }

	  private class ViewHolder {
	   TextView code;
	   CheckBox name;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {

	   ViewHolder holder = null;

	   Log.v("ConvertView", String.valueOf(position));

	   if (convertView == null) {

	    LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	    convertView = vi.inflate(R.layout.state_info, null);

	    holder = new ViewHolder();
	    holder.code = (TextView) convertView.findViewById(R.id.code);
	    holder.name = (CheckBox) convertView
	      .findViewById(R.id.checkBox1);

	    convertView.setTag(holder);

	    holder.name.setOnClickListener(new View.OnClickListener() {
	     public void onClick(View v) {
	      CheckBox cb = (CheckBox) v;
	      PlaceUI _state = (PlaceUI) cb.getTag();

	      Toast.makeText(
	        getApplicationContext(),
	        "Checkbox: " + cb.getText() + " -> "
	          + cb.isChecked(), Toast.LENGTH_LONG)
	        .show();

	      _state.setSelected(cb.isChecked());
	     }
	    });

	   } else {
	    holder = (ViewHolder) convertView.getTag();
	   }

	   PlaceUI state = stateList.get(position);

	   holder.code.setText(" (" + state.getCode() + ")");
	   holder.name.setText(state.getName());
	   holder.name.setChecked(state.isSelected());

	   holder.name.setTag(state);

	   return convertView;
	  }

	 }

	 private void checkButtonClick() {

	  Button myButton = (Button) findViewById(R.id.findSelected);

	  myButton.setOnClickListener(new OnClickListener() {

	   @Override
	   public void onClick(View v) {

	    StringBuffer responseText = new StringBuffer();
	    responseText.append("Selected Countries are...\n");

	    ArrayList<PlaceUI> stateList = dataAdapter.stateList;

	    for (int i = 0; i < stateList.size(); i++) {
	     PlaceUI state = stateList.get(i);

	     if (state.isSelected()) {
	      responseText.append("\n" + state.getName());
	     }
	    }

	    Toast.makeText(getApplicationContext(), responseText,
	      Toast.LENGTH_LONG).show();
	   }
	  });
	 }

	}