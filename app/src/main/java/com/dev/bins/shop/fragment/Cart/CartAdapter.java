package com.dev.bins.shop.fragment.Cart;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.GoodsItem;
import com.dev.bins.shop.widget.CountView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 25/02/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {


    private List<GoodsItem> mCards;
    private Context mContext;
    CartFragment mCartFragment;
    public CartAdapter(List<GoodsItem> cards, CartFragment cartFragment) {
        this.mCards = cards;
        mCartFragment = cartFragment;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        GoodsItem cart = mCards.get(position);
        holder.cb.setChecked(cart.isChecked());
        Picasso.with(mContext).load(cart.getRealImgUrl()).into(holder.iv);
        holder.tvName.setText(cart.getName());
        holder.tvPrice.setText(String.valueOf(cart.getPrice()));
        holder.countView.setmCount(cart.getCount());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCards.get(position).setChecked(isChecked);
                mCards.get(position).save();
                mCartFragment.calcPrice();
            }
        });
        holder.countView.setmOnCountChangeListener(new CountView.onCountChangeListener() {
            @Override
            public void onChnage(int count) {
                if (0 == count){
                    mCards.get(position).delete();
                    mCards.remove(position);
                    notifyDataSetChanged();
                }else {
                    mCards.get(position).setCount(count);
                    mCards.get(position).save();
                }
                mCartFragment.calcPrice();
            }
        });

    }


    @Override
    public int getItemCount() {
        return mCards.size();
    }

    static class Holder extends RecyclerView.ViewHolder {


        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.count)
        CountView countView;
        @BindView(R.id.cb)
        CheckBox cb;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
