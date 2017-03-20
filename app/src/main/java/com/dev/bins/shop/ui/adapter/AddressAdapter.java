package com.dev.bins.shop.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.util.HebrewCalendar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.OrderAddress;
import com.dev.bins.shop.ui.AddAddressActivity;
import com.dev.bins.shop.ui.ManageAddressActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.data;

/**
 * Created by bin on 16/03/2017.
 */

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.Holder> {


    private List<OrderAddress> mAddresses;

    private Activity mContext;

    public AddressAdapter(Activity context, List<OrderAddress> mAddresses) {
        this.mAddresses = mAddresses;
        mContext = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final OrderAddress orderAddress = mAddresses.get(position);
        holder.mCbSetDefault.setOnCheckedChangeListener(null);
        holder.mTvAddressName.setText(orderAddress.getName());
        holder.mTvAddressPhone.setText(orderAddress.getPhone());
        holder.mTvAddressDetail.setText(orderAddress.getAdd() + orderAddress.getAddress());
        holder.mCbSetDefault.setChecked(orderAddress.isDefault());
        holder.mCbSetDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for (OrderAddress mAddress : mAddresses) {
                    if (mAddress.isDefault()) {
                        mAddress.setDefault(false);
                        mAddress.save();
                        notifyItemChanged(mAddresses.indexOf(mAddress));
                        break;
                    }
                }
                orderAddress.setDefault(isChecked);
                orderAddress.save();
                notifyItemChanged(position);
            }
        });
        holder.mTvEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddAddressActivity.class);
                intent.putExtra("id",orderAddress.getId());
                mContext.startActivityForResult(intent,0);
            }
        });
        holder.mTvDelAdrress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderAddress.delete();
                mAddresses.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAddresses.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_add_name)
        TextView mTvAddressName;
        @BindView(R.id.tv_add_phone)
        TextView mTvAddressPhone;
        @BindView(R.id.tv_add_detail)
        TextView mTvAddressDetail;
        @BindView(R.id.tv_edit_address)
        TextView mTvEditAddress;
        @BindView(R.id.tv_del_address)
        TextView mTvDelAdrress;
        @BindView(R.id.cb_set_default)
        CheckBox mCbSetDefault;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
