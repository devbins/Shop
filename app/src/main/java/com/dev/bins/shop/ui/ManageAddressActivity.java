package com.dev.bins.shop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.OrderAddress;
import com.dev.bins.shop.ui.adapter.AddressAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static org.litepal.crud.DataSupport.findAll;

public class ManageAddressActivity extends AppCompatActivity {

    private static final int ADD_ADDRESS = 0;
    @BindView(R.id.recycler_address)
    RecyclerView mRecyclerViewAddress;
    private AddressAdapter mAdapter;
    private List<OrderAddress> mAddresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_address);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mAddresses = findAll(OrderAddress.class);
        mAdapter = new AddressAdapter(this, mAddresses);
        mRecyclerViewAddress.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerViewAddress.setAdapter(mAdapter);
        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float x = e.getX();
                float y = e.getY();
                View child = mRecyclerViewAddress.findChildViewUnder(x, y);
                if (null != child) {
                    int position = mRecyclerViewAddress.getChildLayoutPosition(child);
                    OrderAddress orderAddress = mAddresses.get(position);
                    Intent intent = new Intent();
                    intent.putExtra("id",orderAddress.getId());
                    setResult(RESULT_OK, intent);
                    finish();
                }
                return true;
            }
        });
        mRecyclerViewAddress.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return gestureDetector.onTouchEvent(e);
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                gestureDetector.onTouchEvent(e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @OnClick(R.id.btn_add_address)
    public void addAddress(View view) {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivityForResult(intent, ADD_ADDRESS);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mAddresses.clear();
            mAddresses.addAll(DataSupport.findAll(OrderAddress.class));
            mAdapter.notifyDataSetChanged();
        }
    }
}
