package com.marik.electronicslist.model;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CategoryModel implements Parcelable{

    @SerializedName("category")
    private String category;
    @SerializedName("sub")
    private List<SubCategoryItem> subModel;
    @SerializedName("products")
    private List<ProductModel> mProducts;

    public CategoryModel() {
    }

    private CategoryModel(Parcel in) {
        category = in.readString();
        subModel = in.createTypedArrayList(SubCategoryItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(category);
        dest.writeTypedList(subModel);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CategoryModel> CREATOR = new Creator<CategoryModel>() {
        @Override
        public CategoryModel createFromParcel(Parcel in) {
            return new CategoryModel(in);
        }

        @Override
        public CategoryModel[] newArray(int size) {
            return new CategoryModel[size];
        }
    };

    public List<ProductModel> getProducts() {
        return mProducts;
    }

    public void setProducts(List<ProductModel> products) {
        mProducts = products;
    }

    public String getName() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<SubCategoryItem> getSubModel() {
        return subModel;
    }

    public void setSubModel(List<SubCategoryItem> subModel) {
        this.subModel = subModel;
    }

    public String toString(){
        return getName();
    }
}
