package com.dhakaregency;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dhakaregency.quickkot.R;

/**
 * Created by Administrator on 10/03/2016.
 */
public class button_billing extends Fragment implements Button.OnClickListener {

    Context context;
    Button buttonOne;
    Communicator communicator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.button_billing_layout,container,false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        communicator= (Communicator) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonOne= (Button) view.findViewById(R.id.btn1);
        buttonOne.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                // ОК button
               // communicator.UpdateQty(1);
                break;
            case R.id.btn2:
                // Cancel button
                Toast.makeText(context,"A",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
