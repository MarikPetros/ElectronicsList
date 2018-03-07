package com.marik.electronicslist.model;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class SubCategoryItem implements Parcelable, BaseInterface {

    @SerializedName("subName")
    private String subName;
    @SerializedName("sub")
    private List<com.marik.electronicslist.model.SubCategoryItem> mSubCategoryItems;
    @SerializedName("products")
    private List<ProductModel> mProductModels;

    protected SubCategoryItem(Parcel in) {
        subName = in.readString();
        mSubCategoryItems = in.createTypedArrayList(com.marik.electronicslist.model.SubCategoryItem.CREATOR);
        mProductModels = in.createTypedArrayList(ProductModel.CREATOR);
    }

    public static final Creator<com.marik.electronicslist.model.SubCategoryItem> CREATOR
            = new Creator<com.marik.electronicslist.model.SubCategoryItem>() {
        @Override
        public com.marik.electronicslist.model.SubCategoryItem createFromParcel(Parcel in) {
            return new com.marik.electronicslist.model.SubCategoryItem(in);
        }

        @Override
        public com.marik.electronicslist.model.SubCategoryItem[] newArray(int size) {
            return new com.marik.electronicslist.model.SubCategoryItem[size];
        }
    };

    public String getName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<com.marik.electronicslist.model.SubCategoryItem> getSubCategoryItems() {
        return mSubCategoryItems;
    }

    public void setSubCategoryItems(List<com.marik.electronicslist.model.SubCategoryItem> subCategoryItems) {
        mSubCategoryItems = subCategoryItems;
    }

    public List<ProductModel> getProductModels() {
        return mProductModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        mProductModels = productModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(subName);
        parcel.writeTypedList(mSubCategoryItems);
        parcel.writeTypedList(mProductModels);
    }
}
