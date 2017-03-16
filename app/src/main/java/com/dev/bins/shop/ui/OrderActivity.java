package com.dev.bins.shop.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.GoodsItem;
import com.dev.bins.shop.bean.OrderAddress;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends AppCompatActivity {

    private static final int ADDRESS = 0;
    /**
     * 商品展示列表
     */
    @BindView(R.id.recycler_orderlist)
    RecyclerView mRecyclerViewGoods;

    /**
     * 收件人
     */
    @BindView(R.id.tv_receiver)
    TextView mTvReceiver;

    /**
     * 地址
     */
    @BindView(R.id.tv_addr)
    TextView mTvAddress;

    /**
     * 收件人和地址
     */
    @BindView(R.id.rl_add)
    RelativeLayout mRlReceAddress;

    /**
     * 总价格
     */
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    /**
     * 提交订单
     */
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.tv_good_count)
    TextView mTvGoodsCount;
    private List<GoodsItem> mGoods;
    private OrderAddress mDefaultOrderAddress;
    private GoodsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mGoods = DataSupport.where("isChecked=?", "1").find(GoodsItem.class);
        mTvGoodsCount.setText("共" + String.valueOf(mGoods.size()) + "件");
        List<OrderAddress> orderAddresses = DataSupport.where("isDefault=?", "1").find(OrderAddress.class);
        if (null != orderAddresses && orderAddresses.size() > 0) {
            mDefaultOrderAddress = orderAddresses.get(0);
            mTvReceiver.setText(mDefaultOrderAddress.getName());
            mTvAddress.setText(mDefaultOrderAddress.getAdd() + mDefaultOrderAddress.getAddress());
        }
        mTvTotalPrice.setText("应付款:￥" + String.valueOf(calcPrice()));
        mAdapter = new GoodsAdapter(mGoods);
        mRecyclerViewGoods.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerViewGoods.setAdapter(mAdapter);
    }

    public double calcPrice() {
        if (null == mGoods || 0 == mGoods.size()) {
            return 0;
        }
        double price = 0;
        for (GoodsItem mGood : mGoods) {
            price = mGood.getPrice() * mGood.getCount();
        }
        return price;
    }


    @OnClick(R.id.rl_add)
    public void manageAddress(View view) {
        Intent intent = new Intent(this, ManageAddressActivity.class);
        startActivityForResult(intent,ADDRESS);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
