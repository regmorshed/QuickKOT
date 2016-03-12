package com.dhakaregency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dhakaregency.quickkot.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 08/03/2016.
 */
class SingleRow
        {
            String codes;
            String descriptions;
            String saless;

            SingleRow(String descriptions,String saless)
            {
                this.descriptions=descriptions;
                this.saless=saless;
            }
        }

class SingleRowCheckout
{
    String codes;
    String descriptions;
    String saless;
    String qty;

    SingleRowCheckout(String codes,String descriptions,String qtys,String saless)
    {
        this.codes=codes;
        this.descriptions=descriptions;
        this.saless=saless;
        this.qty=qtys;
    }
}

public class ItemListAdapter extends BaseAdapter {
    ArrayList<SingleRow> list;
    Context _context;
    ItemListAdapter(Context context,ArrayList<Item> listArrayList)
    {
        list=new ArrayList<SingleRow>();
        _context=context;
        int i=0;
        for(Item item:listArrayList){

            list.add(new SingleRow(item.getDescription().concat("(").concat(item.getCode() ).concat(")") ,item.getSales()));
            i++;
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater= (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= layoutInflater.inflate(R.layout.item_single_row, parent,false );
        TextView tdesc= (TextView) row.findViewById(R.id.txtDescription);
        TextView tsales= (TextView) row.findViewById(R.id.txtSalesPrice);

     SingleRow temp= list.get(position);

        tdesc.setText(temp.descriptions);
        tsales.setText(temp.saless);
        return row;
    }
}
