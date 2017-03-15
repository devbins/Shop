package com.dev.bins.shop.fragment.Cart;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.dev.bins.shop.OrderActivity;
import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.GoodsItem;
import com.dev.bins.shop.fragment.BaseFragment;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;


public class CartFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.cart_toolbar)
    Toolbar toolbar;

    @BindView(R.id.cart_recycler)
    RecyclerView mCartRecyclerView;

    @BindView(R.id.btn_calc)
    Button mBtnCalc;
    @BindView(R.id.tv_total_price)
    TextView mTvTotalPrice;
    @BindView(R.id.cb)
    CheckBox mCb;
    double mTotalPrice = 0;
    CartAdapter mCartAdapter;
    private List<GoodsItem> mCards = new ArrayList<>();
    private ItemTouchHelper mHelper;

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
        mCartAdapter = new CartAdapter(mCards, this);
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mCartRecyclerView.setAdapter(mCartAdapter);
        mHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                mCards.get(position).delete();
                mCards.remove(position);
                mCartAdapter.notifyItemRemoved(position);
                calcPrice();
            }
        });
        mHelper.attachToRecyclerView(mCartRecyclerView);
        mCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkAll();
                    calcPrice();
                } else {
                    for (GoodsItem mCard : mCards) {
                        mCard.setChecked(false);
                        mCard.save();
                    }
                }
                mCartAdapter.notifyDataSetChanged();
            }
        });
        mBtnCalc.setOnClickListener(this);

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


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), OrderActivity.class);
        startActivity(intent);
    }
}
