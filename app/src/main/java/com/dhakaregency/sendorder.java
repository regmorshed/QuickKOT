package com.dhakaregency;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class sendorder extends AppCompatActivity  implements Communicator {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendorder);

        final Button buton= (Button) findViewById(R.id.btnFood);
        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_menu_fragment_class fragment = (main_menu_fragment_class) getFragmentManager().findFragmentById(R.id.list_Fragment);
                 fragment.setText("02","1");
                // ami pura beekkol hoia gelam

            }
        });
    }


    @Override
    public void respond(String main_group_id)
    {
        sub_menu_fragment_class subMenuFragmentClass = (sub_menu_fragment_class) getFragmentManager().findFragmentById(R.id.submenu_fragment);
        subMenuFragmentClass.ChangeSubMenu(main_group_id);

    }
}
