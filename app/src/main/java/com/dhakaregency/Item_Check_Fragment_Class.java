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
import android.widget.ListAdapter;
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

    String itemqty="";
    Communicator communicator;
    Activity activity;
    ListView listView;
    Context _context;
    ViewGroup viewGroup;

    ArrayList<SingleRowCheckout> list =new ArrayList<SingleRowCheckout>();
    ArrayAdapter<SingleRowCheckout> adapter;

    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;
    Button buttonSeven;
    Button buttonEight;
    Button buttonNine;
    Button buttonZero;
    Button buttonEnter;
    Button buttonDel;
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
        buttonThree= (Button) view.findViewById(R.id.btn3);
        buttonFour= (Button) view.findViewById(R.id.btn4);
        buttonFive= (Button) view.findViewById(R.id.btn5);
        buttonSix= (Button) view.findViewById(R.id.btn6);
        buttonSeven= (Button) view.findViewById(R.id.btn7);
        buttonEight= (Button) view.findViewById(R.id.btn8);
        buttonNine= (Button) view.findViewById(R.id.btn9);
        buttonZero= (Button) view.findViewById(R.id.btn0);
        buttonEnter= (Button) view.findViewById(R.id.btnEnter);
        buttonDel= (Button) view.findViewById(R.id.btnDel);

        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
        buttonZero.setOnClickListener(this);
        buttonEnter.setOnClickListener(this);
        buttonDel.setOnClickListener(this);

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
            case R.id.btn3: {
                itemqty=itemqty+"3";
                break;
            }

            case R.id.btn4: {
                itemqty=itemqty+"4";
                break;
            }

            case R.id.btn5: {
                itemqty=itemqty+"5";
                break;
            }

            case R.id.btn6: {
                itemqty=itemqty+"6";
                break;
            }

            case R.id.btn7: {
                itemqty=itemqty+"7";
                break;
            }

            case R.id.btn8: {
                itemqty=itemqty+"8";
                break;
            }

            case R.id.btn9: {
                itemqty=itemqty+"9";
                break;
            }

            case R.id.btn0: {
                itemqty=itemqty+"0";
                break;
            }

            case R.id.btnDel: {
                if(selectedIndex!=-1) {
                    SingleRowCheckout singleRowCheckout = (SingleRowCheckout) listView.getItemAtPosition(selectedIndex);
ArrayAdapter<SingleRowCheckout> arrayAdapter= (ArrayAdapter<SingleRowCheckout>) listView.getAdapter();

                    arrayAdapter.remove(singleRowCheckout);
                    arrayAdapter.notifyDataSetChanged();
                }
                break;
            }

            case R.id.btnEnter: {
                if(selectedIndex!=-1) {
                    View vw= listView.getChildAt(selectedIndex);
                 TextView txtqty= ((TextView) vw.findViewById(R.id.txtQty));
                    SingleRowCheckout singleRowCheckout = (SingleRowCheckout) listView.getItemAtPosition(selectedIndex);
                    txtqty.setText(itemqty);
                    itemqty="";
                    //Toast.makeText(_context, singleRowCheckout.descriptions.toString(), Toast.LENGTH_LONG).show();
                }
                break;
            }
            //.... etc
        }

    }
}
