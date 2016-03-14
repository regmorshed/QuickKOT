package com.dhakaregency;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dhakaregency.quickkot.R;

public class preparation extends AppCompatActivity implements  Button.OnClickListener {

    EditText editTextPrep;
    Button buttonPrepEnter;
    String index;
    Communicator communicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation);
        buttonPrepEnter= (Button) findViewById(R.id.btnPreEnter);
        buttonPrepEnter.setOnClickListener(this);
        editTextPrep= (EditText) findViewById(R.id.editTextPrep);
        Bundle b = getIntent().getExtras();
        index= b.getString("index");
        communicator= (Communicator) getApplicationContext();
    }

    @Override
    public void onClick(View v) {
        communicator.UpdatePreparation(editTextPrep.getText().toString());
    }
}
