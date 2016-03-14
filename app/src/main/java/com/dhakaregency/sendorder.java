package com.dhakaregency;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dhakaregency.quickkot.R;

import java.util.ArrayList;

public class sendorder extends AppCompatActivity implements Communicator,Button.OnClickListener {


    private static final String ITEM_LIST = "item_list";
    Button buttonFinalize;
    ListView listFinal;

    String userid;
    String moduleid;
    String tableid;
    String pax;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendorder);

        Bundle b = getIntent().getExtras();

        moduleid= b.getString("moduleId");
        userid= b.getString("userid");
        tableid= b.getString("tableid");

        final Button buton= (Button) findViewById(R.id.btnFood);
        final Button buttonBev=(Button) findViewById(R.id.btnBev);
        final Button buttonShisha=(Button) findViewById(R.id.btnShisha);
        final Button buttonHD=(Button) findViewById(R.id.btnHD);
        final Button buttonOthers=(Button) findViewById(R.id.btnOthers);

        buttonFinalize= (Button) findViewById(R.id.btnFinalize);
       listFinal= (ListView) findViewById(R.id.lstItemCheckout);

        buton.setOnClickListener((View.OnClickListener) this);
        buttonBev.setOnClickListener((View.OnClickListener) this);
        buttonShisha.setOnClickListener((View.OnClickListener) this);
        buttonHD.setOnClickListener((View.OnClickListener) this);
        buttonOthers.setOnClickListener((View.OnClickListener) this);


        buttonFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), final_checkout_bill.class);

                Bundle bundle = new Bundle();
                //Add your data to bundle
                bundle.putString("userid", userid);
                bundle.putString("moduleId", moduleid);
                bundle.putString("tableid", tableid);
                bundle.putString("pax",pax);

                intent .putExtras(bundle);


                ArrayList<Final_Bill> arrayList=new ArrayList<Final_Bill>();
                if(listFinal!=null) {

                    for(int position=0;position<listFinal.getCount();position++)
                    {

                        SingleRowCheckout singlerow= (SingleRowCheckout) listFinal.getItemAtPosition(position);
                        Final_Bill finalbill=new Final_Bill(singlerow.getCodes(), singlerow.getDescriptions(), singlerow.getQty());
                        arrayList.add(finalbill);
                    }

                    intent.putParcelableArrayListExtra(ITEM_LIST, arrayList);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Null",Toast.LENGTH_LONG).show();
                }
            }
        });
        }
    @Override
    public void onClick(View v) {
        int foodtype=0;
        if(v == findViewById(R.id.btnFood)){
            foodtype=0;
        }
        else if(v == findViewById(R.id.btnBev)){
            foodtype=1;
        }
        else if(v == findViewById(R.id.btnShisha)){
            foodtype=3;
        }
        else if(v == findViewById(R.id.btnHD)){
            foodtype=4;
        }else if(v == findViewById(R.id.btnOthers)){
            foodtype=2;
        }

        main_menu_fragment_class fragment = (main_menu_fragment_class) getFragmentManager().findFragmentById(R.id.list_main);
        ArrayList<String> arrayList=new ArrayList<String>();
        arrayList.add(0,moduleid);
        arrayList.add(1,foodtype+"");
        fragment.callMenu(arrayList);
    }
    @Override
    public void LoadSubMenu(String main_group_id) {

        sub_menu_fragment_class subMenuFragmentClass= (sub_menu_fragment_class) getFragmentManager().findFragmentById(R.id.list_sub);
        subMenuFragmentClass.ChangeSubMenu(main_group_id);
    }

    @Override
    public void LoadItemList(String subgroup_id) {

        item_list_fragment_class itemListFragmentClass= (item_list_fragment_class ) getFragmentManager().findFragmentById(R.id.list_item);
        itemListFragmentClass.callMenu(subgroup_id);
    }

    @Override
    public void ParseItem(SingleRowCheckout singleRow) {
        Item_Check_Fragment_Class item_check_fragment_class= (Item_Check_Fragment_Class) getFragmentManager().findFragmentById(R.id.list_billing);
        item_check_fragment_class.SetItemList(singleRow);
    }



}
