package com.marik.electronicslist.model;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MainCategoryModel implements Parcelable{

    @SerializedName("electronics")
    public List<CategoryModel> category;

    public MainCategoryModel() {}

    protected MainCategoryModel(Parcel in) {
        category = in.createTypedArrayList(CategoryModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(category);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainCategoryModel> CREATOR = new Creator<MainCategoryModel>() {
        @Override
        public MainCategoryModel createFromParcel(Parcel in) {
            return new MainCategoryModel(in);
        }

        @Override
        public MainCategoryModel[] newArray(int size) {
            return new MainCategoryModel[size];
        }
    };
}
