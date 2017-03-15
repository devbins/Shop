package com.dev.bins.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.bins.shop.bean.GoodsItem;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;

public class OrderActivity extends AppCompatActivity {

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
    private List<GoodsItem> mGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mGoods = DataSupport.where("isChecked=？", "true").find(GoodsItem.class);

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



}
