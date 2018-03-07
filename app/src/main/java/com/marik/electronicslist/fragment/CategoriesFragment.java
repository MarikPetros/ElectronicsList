package com.marik.electronicslist.fragment;


import com.marik.electronicslist.R;
import com.marik.electronicslist.activity.MainPageActivity;
import com.marik.electronicslist.adapter.CategoriesAdapter;
import com.marik.electronicslist.adapter.SubCategoriesAdapter;
import com.marik.electronicslist.model.BaseInterface;
import com.marik.electronicslist.model.CategoryModel;
import com.marik.electronicslist.model.ProductModel;
import com.marik.electronicslist.model.SubCategoryItem;
import com.marik.electronicslist.util.FetchData;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 *  The Fragment for lists of Categories and Products
 */
public class CategoriesFragment extends Fragment implements SubCategoriesAdapter.OnItemClickListener, CategoriesAdapter.OnItemClickListener {


    private static final String ARG_CATEGORY_MODEL = "category_model";
    private static final String ARG_SUBCATEGORY_ITEM = "sub_category_item";
    public onCategoriesItemClickListener listener;

    private RecyclerView rvSubCategoriesList;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    // factory method of Fragment for Categories
    public static CategoriesFragment getInstance(CategoryModel categoryModel){
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        if (categoryModel != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ARG_CATEGORY_MODEL, categoryModel);
            categoriesFragment.setArguments(bundle);
        }

        return categoriesFragment;
    }

    // factory method of Fragment for SubCategories
    public static CategoriesFragment getInstance(SubCategoryItem subCategoryItem){
        CategoriesFragment categoriesFragment = new CategoriesFragment();
        if (subCategoryItem != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(ARG_SUBCATEGORY_ITEM, subCategoryItem);
            categoriesFragment.setArguments(bundle);
        }

        return categoriesFragment;
    }

    @Override
    public void onSubCategoriesItemClicked(SubCategoryItem catModel, int position) {
        listener.itemClick(catModel);
    }

    @Override
    public void onCategoriesItemClicked(CategoryModel catModel, int position) {
        listener.itemCategoryClick(catModel);
    }

    public interface onCategoriesItemClickListener {

        void itemCategoryClick(CategoryModel categoryModel);

        void itemClick(SubCategoryItem categoryModel);
    }


    private void setRecyclerViewListAdapter(SubCategoriesAdapter subCategoriesAdapter) {
        subCategoriesAdapter.setOnItemClickListener(this);
        LinearLayoutManager subCategoriesLayout = new LinearLayoutManager(getActivity().getApplication());
        rvSubCategoriesList.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        this.rvSubCategoriesList.setLayoutManager(subCategoriesLayout);
        this.rvSubCategoriesList.setAdapter(subCategoriesAdapter);
    }


    private void setRecyclerViewListAdapter(CategoriesAdapter categoriesAdapter) {
        categoriesAdapter.setOnItemClickListener(this);
        LinearLayoutManager subCategoriesLayout = new LinearLayoutManager(getActivity().getApplication());
        rvSubCategoriesList.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        this.rvSubCategoriesList.setLayoutManager(subCategoriesLayout);
        this.rvSubCategoriesList.setAdapter(categoriesAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.sub_categ_fragm, container, false);
        rvSubCategoriesList = fragmentView.findViewById(R.id.rvSubCatList);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            List<BaseInterface> baseInterfaces = null;

            if (getArguments().containsKey(ARG_CATEGORY_MODEL)) {
                CategoryModel categoryModel = getArguments().getParcelable(ARG_CATEGORY_MODEL);
                baseInterfaces = getSubCategoryCategoryArrayList(categoryModel.getSubModel(), categoryModel.getProducts());
            } else if (getArguments().containsKey(ARG_SUBCATEGORY_ITEM)) {
                SubCategoryItem subCategoryItem = getArguments().getParcelable(ARG_SUBCATEGORY_ITEM);
                baseInterfaces = getSubCategoryCategoryArrayList(subCategoryItem.getSubCategoryItems(),
                        subCategoryItem.getProductModels());
            }
            SubCategoriesAdapter subCategoriesAdapter = new SubCategoriesAdapter(getActivity(), baseInterfaces);
            setRecyclerViewListAdapter(subCategoriesAdapter);
        } else {
            // this is main category case
            String language = MainPageActivity.current.getLanguage();
            List<CategoryModel> categoryModels = FetchData.loadJSONFromAsset(getActivity(), language);
            CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), categoryModels);
            setRecyclerViewListAdapter(adapter);
        }
    }

    private List<BaseInterface> getSubCategoryCategoryArrayList(
            List<SubCategoryItem> subModel, List<ProductModel> products) {
        List<BaseInterface> baseInterfaces = new ArrayList<>();
        for (SubCategoryItem item : subModel) {
            if (item.getSubCategoryItems() != null && item.getSubCategoryItems().size() > 0) {
                baseInterfaces.add(item);
            } else if (item.getProductModels() != null && item.getProductModels().size() > 0) {
                baseInterfaces.addAll(item.getProductModels());
            }
        }

        baseInterfaces.addAll(products);

        return baseInterfaces;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (onCategoriesItemClickListener) activity;
    }

}

