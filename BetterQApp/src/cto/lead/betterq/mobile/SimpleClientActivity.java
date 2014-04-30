package cto.lead.betterq.mobile;

/*
 * This is a simple Android mobile client
 * This application read any string message typed on the text field and 
 * send it to the server when the Send button is pressed
 */
 
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cto.lead.betterq.R;
 
public class SimpleClientActivity extends Activity 
{
	private EditText textField;
	private Button button;
	private String message;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_client);

		textField = (EditText) findViewById(R.id.etSimpleClientText); //reference to the text field
		button = (Button) findViewById(R.id.btnSend);   //reference to the send button

		//Button press event listener
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				try{
					message = textField.getText().toString();
					new ConnectToServerTask().execute(message);
					textField.setText("");
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
	  });
	}
	

	private class ConnectToServerTask extends AsyncTask<String, Void, String> 
	{
		 @Override
		 protected void onPreExecute() {}

		 @Override
		 protected void onProgressUpdate(Void... values) {}
		 
		 @Override
		 protected void onPostExecute(String result) {
//		        TextView txt = (TextView) findViewById(R.id.output);
//		        txt.setText("Executed"); // txt.setText(result);
//		        // might want to change "executed" for the returned string passed
//		        // into onPostExecute() but that is upto you
			 
//			    // result is the data returned from doInbackground
//		        IPControl.this.data = result;
//		        if (IPControl.this.pd != null) {
//		            IPControl.this.pd.dismiss();
//		        }
		  }

	
		@Override
	    protected String doInBackground(String... params) {
			Socket client = null;
			try {
				client = new Socket("10.0.2.2", 9090);  //connect to server
			}  catch (UnknownHostException e) {
				e.printStackTrace();
			}  catch (IOException e) {
			 	e.printStackTrace();
			}	
			
			PrintWriter printWriter = null;
			try{
				printWriter = new PrintWriter(client.getOutputStream(),true);
				printWriter.write(message);  //write the message to output stream
				printWriter.flush();
				printWriter.close();
				client.close();   //closing the connection
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
	    	return "Executed"; // input for onPostExecute()
	    }

	}
}


