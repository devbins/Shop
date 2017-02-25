package com.dev.bins.shop.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.Banner;
import com.dev.bins.shop.bean.Recommend;
import com.dev.bins.shop.fragment.BaseFragment;
import com.dev.bins.shop.net.NetworkManager;
import com.dev.bins.shop.widget.MyToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static android.R.attr.banner;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    List<Recommend> mRecommends = new ArrayList<>();

    @BindView(R.id.toolbar)
    MyToolbar mToolbar;
    @BindView(R.id.slider)
    SliderLayout mSliderLayout;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    private Adapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
        super();

    }

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        addBanner();
    }

    private void initRecyclerView() {
        mAdapter = new Adapter(mRecommends);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Subscriber<List<Recommend>> subscriber = new Subscriber<List<Recommend>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Recommend> recommends) {
                mRecommends.addAll(recommends);
                mAdapter.notifyDataSetChanged();
            }
        };
        Subscription subscription = NetworkManager.getInstance().getRecommend(subscriber);
        mSubscriptions.add(subscription);
    }

    private void addBanner() {
        Subscriber<List<Banner>> subscriber = new Subscriber<List<Banner>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Banner> banners) {
                for (Banner banner : banners) {

                    TextSliderView textSliderView = new TextSliderView(getActivity());
                    textSliderView.image(banner.getImgUrl());
                    textSliderView.description(banner.getName());
                    textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                    mSliderLayout.addSlider(textSliderView);
                }
            }
        };
        Subscription subscription = NetworkManager.getInstance().getBanner(subscriber);
        mSubscriptions.add(subscription);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mSubscriptions) {
            mSubscriptions.unsubscribe();
        }
    }
}
