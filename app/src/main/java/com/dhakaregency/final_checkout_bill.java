package com.dhakaregency;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dhakaregency.quickkot.R;

import java.util.ArrayList;

public class final_checkout_bill extends AppCompatActivity {

    ListView listView;
    String moduleid;
    String userid;
    String tableid;
    String pax;
    String preparation;
    Button buttonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_final_checkout_bill);
        listView= (ListView) findViewById(R.id.lstFinalKOT);

        buttonBack= (Button) findViewById(R.id.btnFinalBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle b = getIntent().getExtras();

        moduleid = b.getString("moduleId");
        userid = b.getString("userid");
        tableid = b.getString("tableid");
        pax= b.getString("pax");

        ArrayList<Final_Bill> final_bills  = getIntent().getParcelableArrayListExtra("item_list");
        if(final_bills!=null){

            if(listView!=null) {

                    try {
                        KOTAdapter kotAdapter;
                        kotAdapter= new KOTAdapter (getApplicationContext(), 0, final_bills  );
                        listView.setAdapter(kotAdapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }

    }

    }
