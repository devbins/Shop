package com.dev.bins.shop.fragment.me;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.bins.shop.R;
import com.dev.bins.shop.fragment.BaseFragment;
import com.dev.bins.shop.widget.MyToolbar;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.me_toolbar)
    MyToolbar mToolbar;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_order)
    TextView mTvOrder;

    @BindView(R.id.avatar)
    ImageView mCircleImageView;

    public MeFragment() {
        super();
        // Required empty public constructor
    }

    public static MeFragment newInstance() {

        Bundle args = new Bundle();

        MeFragment fragment = new MeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar.setRightButtonClickListener(this);
        mTvOrder.setOnClickListener(this);
        mTvAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar:
                break;
            case R.id.tv_order:
                break;
            case R.id.tv_address:
                break;
        }
    }
}
