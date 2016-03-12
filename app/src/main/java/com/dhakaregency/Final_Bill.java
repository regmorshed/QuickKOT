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
    private  String itemPrice;
    private String itemTotalPrice;

    public  Final_Bill(){

    }

    public  Final_Bill(String _itemCode,String _itemDescription,String _itemQty,String _itemPrice,String _itemTotal){
        this.itemCode=_itemCode;
        this.itemDescription=_itemDescription;
        this.itemQty=_itemQty;
        this.itemPrice=_itemPrice;
        this.itemTotalPrice=_itemTotal;
    }
    protected Final_Bill(Parcel input) {
        this.itemCode= input.readString();
        this.itemDescription=input.readString();
        this.itemQty=input.readString();
        this.itemPrice=input.readString();
        this.itemTotalPrice=input.readString();
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
        dest.writeString(this.itemCode);
        dest.writeString(this.itemDescription);
        dest.writeString(this.itemQty);
        dest.writeString(this.itemPrice);
        dest.writeString(this.itemTotalPrice);
    }
}
