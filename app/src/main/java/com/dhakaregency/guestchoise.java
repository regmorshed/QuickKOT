package com.dhakaregency;

import android.content.Context;
import android.content.Intent;
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
    LoadGuestInfo loadguestinfo;
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

       loadguestinfo=new LoadGuestInfo();

        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomNo=editTextRoomNo.getText().toString();
                loadguestinfo.execute(roomNo);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void GetRoomGuestInfo( ArrayList<UserInfo> userInfoArrayList) {
        /*
        String registrationno=null;
        int roomstatus=0;

        for(UserInfo userInfo:userInfoArrayList) {
            registrationno=userInfo.getRegistrationNo();
            roomstatus=userInfo.getRoomStatus();
        }
        */
        Intent intent = new Intent(getApplicationContext(),tablechoise.class );
        //Create the bundle
        /*
        Bundle bundle = new Bundle();
        //Add your data to bundle
        bundle.putString("registrationno", registrationno);
        bundle.putString("roomstatus", getString(roomstatus));
        //Add the bundle to the intent
        intent .putExtras(bundle); */
        startActivity(intent);

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
    public class LoadGuestInfo extends AsyncTask<String, Void,  ArrayList<UserInfo>>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute( ArrayList<UserInfo>  userInfo) {
            super.onPostExecute(userInfo);
            GetRoomGuestInfo(userInfo);

        }

        @Override
        protected  ArrayList<UserInfo>  doInBackground(String... params) {

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
            ArrayList<UserInfo> userInfoArrayList=new ArrayList<UserInfo>();


            try {
                JSONArray jsonArray = (JSONArray) jObject.getJSONArray("GetRoomGuestResult");
                try {


                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        UserInfo userInfo1= new UserInfo();
                        userInfo1.setRegistrationNo(object.getString("registration"));
                        userInfo1.setRoomStatus(object.getInt("alive"));
                        userInfoArrayList.add(userInfo1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return userInfoArrayList;
        }
    }
}
