package com.fcl.mylibrary;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by derik on 17-1-9.
 */

public class UserInfo implements Parcelable{
    private String id;
    private String userName;
    private String password;
    private String sex;
    private int age;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String address;

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserInfo(){

    }

    public UserInfo(Parcel in){
        id = in.readString();
        userName = in.readString();
        password = in.readString();
        sex = in.readString();
        age = in.readInt();
        birthday = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        address = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(sex);
        dest.writeInt(age);
        dest.writeString(birthday);
        dest.writeString(phoneNumber);
        dest.writeString(email);
        dest.writeString(address);

    }

    public void readFromParcel(Parcel in){
        id = in.readString();
        userName = in.readString();
        password = in.readString();
        sex = in.readString();
        age = in.readInt();
        birthday = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
        address = in.readString();

    }
}
