package com.dev.bins.shop.fragment.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.GoodsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 25/02/2017.
 */

public class CategoryContentAdapter extends RecyclerView.Adapter<CategoryContentAdapter.Holder> {

    List<GoodsItem> mCategoryDatas;
    private Context mContext;

    public CategoryContentAdapter(List<GoodsItem> categoryDatas) {
        mCategoryDatas = categoryDatas;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_data, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Picasso.with(mContext).load(mCategoryDatas.get(position).getImgUrl()).into(holder.iv);
        holder.tvName.setText(mCategoryDatas.get(position).getName());
        holder.tvPrice.setText(String.valueOf(mCategoryDatas.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return mCategoryDatas.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
