package com.dev.bins.shop.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dev.bins.shop.bean.OrderAddress;

import java.util.List;

/**
 * Created by bin on 16/03/2017.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.Holder> {


    private List<OrderAddress> mAddresses;


    public AddressAdapter(List<OrderAddress> mAddresses) {
        this.mAddresses = mAddresses;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mAddresses.size();
    }

    static class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }

}
