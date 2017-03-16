package com.dev.bins.shop.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.GoodsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 15/03/2017.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.Holder> {


    private List<GoodsItem> mGoods;
    private Context mContext;

    public GoodsAdapter(List<GoodsItem> goods) {
        this.mGoods = goods;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Picasso.with(mContext).load(mGoods.get(position).getRealImgUrl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goods)
        ImageView iv;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
