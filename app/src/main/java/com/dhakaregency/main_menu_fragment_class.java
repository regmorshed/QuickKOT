package com.dhakaregency;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

/**
 * Created by Administrator on 29/02/2016.
 */
public class main_menu_fragment_class extends Fragment
        {
           Communicator communicator;
ListView listView;
Activity activity;
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                return inflater.inflate(R.layout.main_menu_layout,container,false);
            }

            @Override
            public void onActivityCreated(Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                listView= (ListView) getView().findViewById(R.id.lstMainMenu);

            }

            @Override
            public void onAttach(Context context) {
                super.onAttach(context);
                activity= (Activity) context;
                communicator= (Communicator) activity;
            }

            public void callMenu(  ArrayList<String> arrayList){
                if(listView!=null) {
                    GetMainMenu getMainMenu=new GetMainMenu();
                    getMainMenu.execute();
                }

            }

            public  void populateMenuList(ArrayList<MenuList> listArrayList)
            {
               ArrayList<String> arrayList=new ArrayList<>();
              for(MenuList menuList:listArrayList)
              {
                  arrayList.add(menuList.getDescription());
              }
              ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(activity,android.R.layout.simple_list_item_1,arrayList);

              listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getActivity(),(String)listView.getItemAtPosition(position),Toast.LENGTH_LONG).show();
                        String menucode=(String)((String) listView.getItemAtPosition(position)).substring(0,1);

                        //TODO call sub menu from here
                        communicator.LoadSubMenu(menucode);
                    }
                });

            }




            public class GetMainMenu extends AsyncTask<ArrayList<String>, Void, ArrayList<MenuList>> {

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();

                }

                @Override
                protected void onPostExecute(ArrayList<MenuList> menuListArrayList) {
                    super.onPostExecute(menuListArrayList);
                    populateMenuList(menuListArrayList);
                }

                @Override
                protected ArrayList<MenuList> doInBackground(ArrayList<String>... params) {

                    String str = "http://192.168.99.12:8080/AuthService.svc/GetMenuList";
                    String response = "";
                    ArrayList<MenuList> menuListArrayList= new ArrayList<>();

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
                     //   ArrayList<String> passed = params[0];

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
                                .key("moduleid").value("02")//Todo place your variable here
                                .key("foodtype").value("1")//Todo place your variable here
                                .endObject();

                        //byte[] outputBytes = jsonParam.toString().getBytes("UTF-8");
                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                            outputStreamWriter.write(userJson.toString());
                            outputStreamWriter.close();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        int responseCode =0;
                        try {
                            responseCode =conn.getResponseCode();
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }

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
                        JSONArray jsonArray = (JSONArray) jObject.getJSONArray("GetMenuListResult");
                        try {

                            for (int i=0;i<jsonArray.length();i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                MenuList outletsEntity = new MenuList();
                                outletsEntity .setCode(object.getString("code"));
                                outletsEntity.setDescription(object.getString("description"));
                                menuListArrayList.add(outletsEntity);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return menuListArrayList;
                }

            }

}
