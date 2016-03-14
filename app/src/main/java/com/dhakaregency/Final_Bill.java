package com.dhakaregency;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 12/03/2016.
 */
public class Final_Bill implements Parcelable{

    private  String itemCode;
    private String itemDescription;
    private String itemQty;

    public  Final_Bill(){

    }

    public  Final_Bill(String _itemCode,String _itemDescription,String _itemQty){
        this.setItemCode(_itemCode);
        this.setItemDescription(_itemDescription);
        this.setItemQty(_itemQty);

    }
    public Final_Bill(Parcel input) {
        this.setItemCode(input.readString());
        this.setItemDescription(input.readString());
        this.setItemQty(input.readString());
    }

    public static final Creator<Final_Bill> CREATOR = new Creator<Final_Bill>() {
        @Override
        public Final_Bill createFromParcel(Parcel in) {
            return new Final_Bill(in);
        }

        @Override
        public Final_Bill[] newArray(int size) {
            return new Final_Bill[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getItemCode());
        dest.writeString(this.getItemDescription());
        dest.writeString(this.getItemQty());
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemQty() {
        return itemQty;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }
}
