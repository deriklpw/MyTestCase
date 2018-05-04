package com.derik.demo.c_third.serialparcel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by derik on 16-12-26.
 */

public class ParcelableTest implements Parcelable {

    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParcelableTest(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected ParcelableTest(Parcel in) {
        id = in.readInt();
        name = in.readString();
        password = in.readString();
    }

    public static final Creator<ParcelableTest> CREATOR = new Creator<ParcelableTest>() {
        @Override
        public ParcelableTest createFromParcel(Parcel in) {
            return new ParcelableTest(in);
        }

        @Override
        public ParcelableTest[] newArray(int size) {
            return new ParcelableTest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(password);

    }


    public void readFromParcel(Parcel parcel){
        id = parcel.readInt();
        name = parcel.readString();
        password = parcel.readString();

    }

}
