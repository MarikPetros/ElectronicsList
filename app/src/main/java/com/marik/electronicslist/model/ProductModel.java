package com.marik.electronicslist.model;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductModel implements BaseInterface, Parcelable {

    @SerializedName("name")
    private String name;

    public ProductModel() {
    }

    protected ProductModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<ProductModel> CREATOR = new Creator<ProductModel>() {
        @Override
        public ProductModel createFromParcel(Parcel in) {
            return new ProductModel(in);
        }

        @Override
        public ProductModel[] newArray(int size) {
            return new ProductModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setProductName(String productName) {
        this.name = productName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
