package com.dhakaregency;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.dhakaregency.quickkot.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class guestchoise extends AppCompatActivity {

    EditText editTextRoomNo;
    RadioButton radioButtonWalking;
    RadioButton radioButtonRoom;
    Button buttonProceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestchoise);
        editTextRoomNo=(EditText)findViewById( R.id.roomEditText);
        radioButtonWalking=(RadioButton)findViewById( R.id.radioWalking);
        radioButtonRoom=(RadioButton)findViewById( R.id.radioRoom);
        buttonProceed= (Button) findViewById(R.id.btnDisplay);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

      final   LoadGuestInfo loadguestinfo=new LoadGuestInfo();

        buttonProceed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadguestinfo.execute("7002");
             //   Toast.makeText(getApplicationContext(),"sdhfjsh",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetRoomGuestInfo(UserInfo userInfo) {

           Toast.makeText(getApplicationContext(),userInfo.getRegistrationNo().toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        editTextRoomNo.setFocusableInTouchMode(true);
        editTextRoomNo.requestFocus();
    }
    public boolean validate() {
        boolean valid = true;
        if(radioButtonWalking.isChecked())
        {
            valid = true;
        }
        if(radioButtonRoom.isChecked()) {
            String roomno= editTextRoomNo.getText().toString();
            if (roomno.isEmpty() ) {
                editTextRoomNo.setError("enter room no");
                valid = false;
            }
        }
        return valid;
    }
    public class LoadGuestInfo extends AsyncTask<String, Void, UserInfo>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(UserInfo  userInfo) {
            super.onPostExecute(userInfo);
            GetRoomGuestInfo(userInfo);

        }

        @Override
        protected UserInfo doInBackground(String... params) {

            String str = "http://192.168.99.12:8080/AuthService.svc/GuestInfo";
            String response = "";
            UserInfo userInfo=new UserInfo();

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

String roomno=params[0].toString();
                JSONObject jsonObject = new JSONObject();
                // Build JSON string
                JSONStringer userJson = new JSONStringer()
                        .object()
                        .key("roomno").value(roomno)
                        .endObject();

                //byte[] outputBytes = jsonParam.toString().getBytes("UTF-8");
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(userJson.toString());
                outputStreamWriter.close();

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
            Integer result = 0;
            JSONObject jObject = null;
            if (!response.isEmpty()) {
                try {
                    jObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

                try {

                    if (jObject != null) {
                        JSONArray jsonArray = (JSONArray) jObject.getJSONArray("GetRoomGuestResult");
                    }


                    // userInfo.setRegistrationNo(jObject("sfsdf").getString("registration"));
                        // userInfo.setRoomStatus(jObject.getInt("alive"));
                                    }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }



            return userInfo;
        }
    }
}
