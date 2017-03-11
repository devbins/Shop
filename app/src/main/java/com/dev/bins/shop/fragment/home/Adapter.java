package com.dev.bins.shop.fragment.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.Recommend;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 24/02/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private List<Recommend> mRecommends;
    private Context mContext;

    public Adapter(List<Recommend> recommends) {
        mRecommends = recommends;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvTitle.setText(mRecommends.get(position).getName());
        Picasso.with(mContext).load(mRecommends.get(position).getPromote3().getImgUrl()).into(holder.iv1);
        Picasso.with(mContext).load(mRecommends.get(position).getPromote2().getImgUrl()).into(holder.iv2);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.home_item;
    }

    @Override
    public int getItemCount() {
        return mRecommends.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv1)
        ImageView iv1;
        @BindView(R.id.iv2)
        ImageView iv2;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
