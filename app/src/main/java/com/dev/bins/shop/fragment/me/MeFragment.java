package com.dev.bins.shop.fragment.me;


import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dev.bins.shop.AddAddressActivity;
import com.dev.bins.shop.OrderActivity;
import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.City;
import com.dev.bins.shop.bean.District;
import com.dev.bins.shop.bean.Province;
import com.dev.bins.shop.fragment.BaseFragment;
import com.dev.bins.shop.widget.MyToolbar;

import org.xml.sax.HandlerBase;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;

import static java.security.AccessController.getContext;

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
                Intent intent = new Intent(getContext(), OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_address:
                Intent intentaddress = new Intent(getContext(), AddAddressActivity.class);
                startActivity(intentaddress);
                break;
        }
    }
}
