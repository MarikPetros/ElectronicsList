package com.marik.electronicslist.adapter;


import com.marik.electronicslist.R;
import com.marik.electronicslist.model.BaseInterface;
import com.marik.electronicslist.model.SubCategoryItem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.SubCategoriesViewHolder> {

    public interface OnItemClickListener {

        void onSubCategoriesItemClicked(SubCategoryItem catModel, int position);
    }

    private List<BaseInterface> catList;

    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    public SubCategoriesAdapter(Context context, List<BaseInterface> catList) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.catList = catList;
    }

    @Override
    public int getItemCount() {
        return (this.catList != null) ? this.catList.size() : 0;
    }

    @Override
    public SubCategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row, parent, false);
        return new SubCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubCategoriesViewHolder holder, final int position) {
        final BaseInterface catModel = this.catList.get(position);
        holder.textViewTitle.setText(catModel.getName());
        if (catModel instanceof SubCategoryItem) {
            holder.imgArrow.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(v -> {
                if (SubCategoriesAdapter.this.onItemClickListener != null) {
                    SubCategoriesAdapter.this.onItemClickListener.onSubCategoriesItemClicked((SubCategoryItem) catModel, position);
                }
            });
        } else {
            holder.imgArrow.setVisibility(View.GONE);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setCatList(List<BaseInterface> catDataList) {
        this.validateCatCollection(catDataList);
        this.catList = catDataList;
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateCatCollection(List<BaseInterface> catDataList) {
        if (catDataList == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class SubCategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imgArrow;

        SubCategoriesViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textName);
            imgArrow = itemView.findViewById(R.id.imgArrow);
        }
    }
}
