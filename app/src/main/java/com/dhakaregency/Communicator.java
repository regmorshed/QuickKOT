package com.dhakaregency;

/**
 * Created by Administrator on 02/03/2016.
 */
public interface Communicator {
    public void LoadSubMenu(String main_group_id);
    public void LoadItemList(String subgroup_id);
    public void ParseItem(SingleRow singleRow);

}
