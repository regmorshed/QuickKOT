package com.dhakaregency;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by Administrator on 23/02/2016.
 */
public class UserAuthCheck extends AsyncTask<ArrayList<String>, Void, Integer> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Integer i) {
        super.onPostExecute(i);
    }

    @Override
    protected Integer  doInBackground(ArrayList<String>... params) {



        String str = "http://192.168.99.12:8080/AuthService.svc/GetAuth";
        String response="";
        URL url=null;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {


            HttpURLConnection conn=null;
            try {
                conn = (HttpURLConnection) url.openConnection();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            ArrayList<String> passed = params[0];

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            // Build JSON string
            JSONStringer userJson = new JSONStringer()
                    .object()
                    .key("user")
                    .object()
                    .key("userid").value(passed.get(0).toString())
                    .key("password").value(passed.get(1).toString())
                    .endObject()
                    .endObject();

            //byte[] outputBytes = jsonParam.toString().getBytes("UTF-8");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
            outputStreamWriter.write(userJson.toString());
            outputStreamWriter.close();

            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer result=0;
        if (!response.isEmpty()) {


                result= Integer.parseInt(response);;

        }



        return result;
    }

}