package com.marik.electronicslist.adapter;

import com.marik.electronicslist.R;
import com.marik.electronicslist.model.CategoryModel;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    public interface OnItemClickListener {

        void onCategoriesItemClicked(CategoryModel catModel, int position);
    }

    private List<CategoryModel> catList;

    private final LayoutInflater layoutInflater;

    private CategoriesAdapter.OnItemClickListener onItemClickListener;

    public CategoriesAdapter(Context context, List<CategoryModel> catList) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.catList = catList;
    }

    @Override
    public int getItemCount() {
        return (this.catList != null) ? this.catList.size() : 0;
    }

    @Override
    public CategoriesAdapter.CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row, parent, false);
        return new CategoriesAdapter.CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        final CategoryModel catModel = this.catList.get(position);
        holder.imgArrow.setVisibility(View.VISIBLE);
        holder.textViewTitle.setText(catModel.getName());
        holder.itemView.setOnClickListener(v -> {
            if (CategoriesAdapter.this.onItemClickListener != null) {
                CategoriesAdapter.this.onItemClickListener.onCategoriesItemClicked(catModel, position);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickListener(CategoriesAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imgArrow;

        CategoriesViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textName);
            imgArrow = itemView.findViewById(R.id.imgArrow);
        }
    }
}
