package com.dhakaregency;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dhakaregency.quickkot.R;

/**
 * Created by Administrator on 29/02/2016.
 */
public class sub_menu_fragment_class extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sub_menu_layout,container,false);
    }
}
