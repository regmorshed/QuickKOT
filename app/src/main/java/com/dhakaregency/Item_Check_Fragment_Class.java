package com.dhakaregency;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dhakaregency.quickkot.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 29/02/2016.
 */
public class Item_Check_Fragment_Class extends Fragment implements  Button.OnClickListener{

    String itemqty="0";
    Communicator communicator;
    Activity activity;
    ListView listView;
    Context _context;
    ViewGroup viewGroup;

    ArrayList<SingleRowCheckout> list =new ArrayList<SingleRowCheckout>();
    ArrayAdapter<SingleRowCheckout> adapter;

    Button buttonOne;
    Button buttonTwo;
    Button buttonEnter;
    int selectedIndex=-1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.viewGroup=container;
      return   inflater.inflate(R.layout.item_check_layout,container,false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
        _context=context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView= (ListView) getView().findViewById(R.id.lstItemCheckout);
        adapter = new ArrayAdapter<SingleRowCheckout>(_context, R.layout.single_row_checkout, list);

        buttonOne= (Button) view.findViewById(R.id.btn1);
        buttonTwo= (Button) view.findViewById(R.id.btn2);
        buttonEnter= (Button) view.findViewById(R.id.btnEnter);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonEnter.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.LTGRAY);
                view.setSelected(true);
                selectedIndex=position;
            }
        });
    }

    public void SetItemList(SingleRowCheckout singleRow)
    {
        if(listView!=null) {
            try {
                list.add(singleRow);

                CheckoutBillAdapter checkoutBillAdapter;
                ArrayList<SingleRowCheckout> myListItems = new ArrayList<SingleRowCheckout>();
//then populate myListItems

                checkoutBillAdapter = new CheckoutBillAdapter(_context, 0, list);
                listView.setAdapter(checkoutBillAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case  R.id.btn1: {
                itemqty="1";
                break;
            }

            case R.id.btn2: {
                itemqty=itemqty+"2";
                break;
            }

            case R.id.btnEnter: {
                if(selectedIndex!=-1) {
                    View vw= listView.getChildAt(selectedIndex);
                 TextView txtqty= ((TextView) vw.findViewById(R.id.txtQty));
                    SingleRowCheckout singleRowCheckout = (SingleRowCheckout) listView.getItemAtPosition(selectedIndex);
                    txtqty.setText(itemqty + "");
                    //Toast.makeText(_context, singleRowCheckout.descriptions.toString(), Toast.LENGTH_LONG).show();
                }
                break;
            }
            //.... etc
        }

    }
}
