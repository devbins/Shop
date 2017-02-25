package com.dev.bins.shop.fragment.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.Category;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 25/02/2017.
 */

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.Holder> {
    List<Category> mCategories;

    public CategoryItemAdapter(List<Category> categories) {
        mCategories = categories;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvNmae.setText(mCategories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvNmae;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
