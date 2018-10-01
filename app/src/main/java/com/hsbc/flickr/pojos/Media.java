package com.hsbc.flickr.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Media implements Parcelable {

    @SerializedName("m")
    private String m;

    public Media(Parcel in) {
        m = in.readString();
    }

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    public Media() {

    }

    public void setM(String m) {
        this.m = m;
    }

    public String getM() {
        return m;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(m);
    }
}