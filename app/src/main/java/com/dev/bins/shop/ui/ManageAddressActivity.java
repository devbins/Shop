package com.dev.bins.shop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.OrderAddress;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManageAddressActivity extends AppCompatActivity {

    private static final int ADD_ADDRESS = 0;
    @BindView(R.id.recycler_address)
    private RecyclerView mRecyclerViewAddress;

    private List<OrderAddress> mAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mAddresses = DataSupport.findAll(OrderAddress.class);

    }

    @OnClick(R.id.btn_add_address)
    public void addAddress(View view) {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivityForResult(intent, ADD_ADDRESS);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
