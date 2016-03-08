package com.dhakaregency;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.dhakaregency.quickkot.R;

/**
 * Created by Administrator on 29/02/2016.
 */
public class Item_Check_Fragment_Class extends Fragment {

    Communicator communicator;
    Activity activity;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      return   inflater.inflate(R.layout.item_check_layout,container,false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView= (ListView) getView().findViewById(R.id.lstItemCheckout);
    }
    public void SetItemList(SingleRow singleRow )
    {
        Toast.makeText(activity,singleRow.descriptions,Toast.LENGTH_LONG).show();
    }
}
