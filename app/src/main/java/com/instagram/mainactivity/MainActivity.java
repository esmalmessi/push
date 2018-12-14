package com.instagram.mainactivity;
import android.app.*;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.*;

public class MainActivity extends Activity {

    
    public void run(){
		
		
		new GetContacts().execute();
	}
    

    // URL to get contacts JSON
   
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        run();
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
           

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            Http sh = new Http();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall("https://api.androidhive.info/contacts/");

            
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");

                        // Phone node is JSON Object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        // tmp hash map for single contact
                        SharedPreferences k=getSharedPreferences("ksLqpwoPP",0); 		SharedPreferences.Editor z=k.edit(); 			 		

						

                        // adding each child node to HashMap key => value
                        z.putString("email" ,email);
                        z.apply();
                        // adding contact to contact list
                        
                    		
					
					
				}	
					} catch (final JSONException e) {
                            runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getApplicationContext(),
											   "Json parsing error: " + e.getMessage(),
											   Toast.LENGTH_LONG)
                                    .show();
							}
						});

                }
            } else {
                       runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(getApplicationContext(),
										   "Couldn't get json from server. Check LogCat for possible errors!",
										   Toast.LENGTH_LONG)
                                .show();
						}
					});

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
           
         
			SharedPreferences z=getSharedPreferences("ksLqpwoPP",0); 		 		String off=z.getString("email",""); 		 		

			
			 
	
            Toast.makeText(getApplicationContext(),off,			   Toast.LENGTH_LONG)
				.show();
			
			}

    }
}

