package com.example.lawson.androidsummery.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ian.Lu on 2018/12/11.
 * Project : AndroidSummary
 */
public class ModelThree implements Parcelable {

    public String name;

    public ModelThree() {
    }

    protected ModelThree(Parcel in) {
        name = in.readString();
    }

    public static final Creator<ModelThree> CREATOR = new Creator<ModelThree>() {
        @Override
        public ModelThree createFromParcel(Parcel in) {
            return new ModelThree(in);
        }

        @Override
        public ModelThree[] newArray(int size) {
            return new ModelThree[size];
        }
    };

    @Override
    public String toString() {
        return "ModelThree{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
