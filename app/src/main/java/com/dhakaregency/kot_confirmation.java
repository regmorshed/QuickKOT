package com.dhakaregency;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dhakaregency.quickkot.R;

public class kot_confirmation extends AppCompatActivity {

    TextView textView;
    Button buttonBackToTable;
    String userid;
    String moduleid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kot_confirmation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = (TextView) findViewById(R.id.txtKOTFinalNumber);
        buttonBackToTable= (Button) findViewById(R.id.btnBackToTable);

        Bundle b = getIntent().getExtras();
        userid= b.getString("userid");
        moduleid=b.getString("moduleId");

    String kotnumber =b.getString("kot");

        textView.setText(kotnumber );

        buttonBackToTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),tablechoise.class);
                Bundle bundle=new Bundle();
                bundle.putString("moduleId",moduleid);
                bundle.putString("userid",userid);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
