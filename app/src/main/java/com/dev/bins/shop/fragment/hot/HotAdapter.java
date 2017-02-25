package com.dev.bins.shop.fragment.hot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.GoodsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 25/02/2017.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.Holder> {
    private List<GoodsItem> mGoods;
    private Context mContext;
    public HotAdapter(List<GoodsItem> goods) {
        this.mGoods = goods;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new Holder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_hot, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        Picasso.with(mContext).load(mGoods.get(position).getImgUrl()).into(holder.imageView);
        holder.tvName.setText(mGoods.get(position).getName());
        holder.tvPrice.setText(String.valueOf(mGoods.get(position).getPrice()));
        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, position+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGoods.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView imageView;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.btn_buy)
        Button btnBuy;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
