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

import com.dev.bins.shop.R;
import com.dev.bins.shop.fragment.BaseFragment;
import com.dev.bins.shop.widget.MyToolbar;

import butterknife.BindView;

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


    CartAdapter mCartAdapter;

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
        mCartAdapter = new CartAdapter();
        mCartRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mCartRecyclerView.setAdapter(mCartAdapter);
    }
}
