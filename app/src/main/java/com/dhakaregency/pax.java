package com.dhakaregency;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.dhakaregency.quickkot.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class pax extends AppCompatActivity  {

    String userid;
    String moduleid;
    String tableid;
    Button buttonMenu;
    EditText editTextPax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pax);


        Bundle b = getIntent().getExtras();

        moduleid= b.getString("moduleId");
        userid= b.getString("userid");
        tableid= b.getString("tableid");


        editTextPax= (EditText) findViewById(R.id.input_pax);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);


        buttonMenu= (Button) findViewById(R.id.btn_menue);
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent intent=new Intent(getApplicationContext(),sendorder.class );

                String pax=editTextPax.getText().toString();

                Bundle bundle = new Bundle();
                //Add your data to bundle
                bundle.putString("userid", userid);
                bundle.putString("moduleId", moduleid);
                bundle.putString("tableid", tableid);
                bundle.putString("pax",pax);

                intent .putExtras(bundle);

               startActivity(intent);
               // PrintKOT printKOT=new PrintKOT();
              //  printKOT.execute();

            }
        });

    }



    public class PrintKOT extends AsyncTask<String, Void, Integer>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }

        @Override
        protected Integer doInBackground(String... params) {
            String str = "http://192.168.99.12:8080/AuthService.svc/Print";
            String response = "";
            URL url = null;
            try {
                url = new URL(str);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {

                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) url.openConnection();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.setRequestProperty("Content-Type", "application/json");
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    response = "";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 1;
        }
    }
}
