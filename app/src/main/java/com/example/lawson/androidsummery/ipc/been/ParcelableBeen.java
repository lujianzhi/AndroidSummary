package com.example.lawson.androidsummery.ipc.been;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ian.Lu on 2017/8/30.
 * Project : AndroidSummary
 */

public class ParcelableBeen implements Parcelable {
    private int age;
    private String name;

    public ParcelableBeen(int age, String name) {
        this.age = age;
        this.name = name;
    }

    protected ParcelableBeen(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public static final Creator<ParcelableBeen> CREATOR = new Creator<ParcelableBeen>() {
        @Override
        public ParcelableBeen createFromParcel(Parcel in) {
            return new ParcelableBeen(in);
        }

        @Override
        public ParcelableBeen[] newArray(int size) {
            return new ParcelableBeen[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
    }

    @Override
    public String toString() {
        return "ParcelableBeen{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
