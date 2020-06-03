package com.example.edwardadmin.aidlmodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by EdwardAdmin on 2017/9/24.
 */

public class Person implements Parcelable {

    private String mUserName;
    private String mUserAge;

    public Person(String username, String userage) {
        mUserName = username;
        mUserAge = userage;
    }

    public String getmUserName() {
        return mUserName;
    }

    public String getmUserAge() {
        return mUserAge;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    protected Person(Parcel in) {
        mUserName = in.readString();
        mUserAge = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUserName);
        dest.writeString(mUserAge);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

}
