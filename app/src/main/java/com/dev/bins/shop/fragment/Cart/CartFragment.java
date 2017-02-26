package com.dev.bins.shop.fragment.Cart;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.Cart;
import com.dev.bins.shop.bean.GoodsItem;
import com.dev.bins.shop.fragment.BaseFragment;
import com.dev.bins.shop.widget.MyToolbar;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Observable;

import static android.R.attr.data;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment {
    @BindView(R.id.cart_toolbar)
    MyToolbar toolbar;

    @BindView(R.id.cart_recycler)
    RecyclerView mCartRecyclerView;

    @BindView(R.id.btn_calc)
    Button btnCalc;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.cb)
    CheckBox mCb;
    double mTotalPrice = 0;
    CartAdapter mCartAdapter;
    private List<GoodsItem> mCards = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {

        Bundle args = new Bundle();

        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cart;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCartAdapter = new CartAdapter(mCards,this);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mCartRecyclerView.setAdapter(mCartAdapter);
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkAll();
                    calcPrice();
                    mCartAdapter.notifyDataSetChanged();
                }else {
                    for (GoodsItem mCard : mCards) {
                        mCard.setChecked(false);
                        mCard.save();
                    }
                }
            }
        });
        getData();
    }

    private void checkAll() {
        for (GoodsItem mCard : mCards) {
            mCard.setChecked(true);
            mCard.save();
        }
    }

    public void calcPrice() {
        mTotalPrice = 0;
        for (GoodsItem mCard : mCards) {
            if (mCard.isChecked()) {
                mTotalPrice += mCard.getPrice() * mCard.getCount();
            }
        }
        mTvTotalPrice.setText("￥" + mTotalPrice);
    }


    public void getData() {
        List<GoodsItem> carts = DataSupport.findAll(GoodsItem.class);
        mCards.clear();
        mCards.addAll(carts);
        calcPrice();
        mCartAdapter.notifyDataSetChanged();
    }


}
