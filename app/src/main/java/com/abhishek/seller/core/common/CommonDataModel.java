package com.abhishek.seller.core.common;

import android.os.Parcel;
import android.os.Parcelable;

public class CommonDataModel implements Parcelable {

    private int source;

    private Status clickType;

    private Object object;

    public CommonDataModel( ) {

    }

    protected CommonDataModel(Parcel in) {
        source = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(source);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommonDataModel> CREATOR = new Creator<CommonDataModel>() {
        @Override
        public CommonDataModel createFromParcel(Parcel in) {
            return new CommonDataModel(in);
        }

        @Override
        public CommonDataModel[] newArray(int size) {
            return new CommonDataModel[size];
        }
    };

    public Status getClickType() {
        return clickType;
    }

    public void setClickType(Status clickType) {
        this.clickType = clickType;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }











}
