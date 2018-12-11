package com.example.lawson.androidsummery.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ian.Lu on 2018/12/11.
 * Project : AndroidSummary
 */
public class ModelOne implements Parcelable {

    public String name = "1";

    private ModelTwo mModelTwo;

    public ModelThree mModelThree;

    public ModelOne(ModelTwo modelTwo, ModelThree modelThree) {
        mModelTwo = modelTwo;
        mModelThree = modelThree;
    }

    protected ModelOne(Parcel in) {
        mModelThree = in.readParcelable(ModelThree.class.getClassLoader());
    }

    public static final Creator<ModelOne> CREATOR = new Creator<ModelOne>() {
        @Override
        public ModelOne createFromParcel(Parcel in) {
            return new ModelOne(in);
        }

        @Override
        public ModelOne[] newArray(int size) {
            return new ModelOne[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mModelThree, flags);
    }

    @Override
    public String toString() {
        return "ModelOne{" +
                "mModelTwo=" + mModelTwo +
                ", mModelThree=" + mModelThree +
                '}';
    }
}
