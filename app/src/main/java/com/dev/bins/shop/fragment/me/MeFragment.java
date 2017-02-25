package com.dev.bins.shop.fragment.me;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dev.bins.shop.R;
import com.dev.bins.shop.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {


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


}
