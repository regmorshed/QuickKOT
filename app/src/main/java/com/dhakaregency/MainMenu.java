package com.dhakaregency;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.dhakaregency.quickkot.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 28/02/2016.
 */
public class MainMenu extends Fragment{

    TableLayout tableLayout;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tableLayout= (TableLayout) container.findViewById(R.id.tablelaoutMainMenu);
       // PopulateOutlets();
        return inflater.inflate(R.layout.main_menu,container,false);

    }
    public void PopulateOutlets() {
        boolean isFirstTime = true;
        boolean isColumnCountingFinished = false;


        TableRow tableRow = null;
        tableRow = new TableRow(getActivity());
        tableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT, 1.0f
        ));
        tableLayout.addView(tableRow);

        final Button button = new Button(getActivity());
        button.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.MATCH_PARENT, 1.0f
        ));

        button.setPadding(0, 0, 0, 0);

        tableRow.addView(button);




    }
}
