package com.dev.bins.shop.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.dev.bins.shop.R;
import com.dev.bins.shop.ShopDetailActivity;
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
    Toolbar mToolbar;
    @BindView(R.id.slider)
    SliderLayout mSliderLayout;
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private GestureDetectorCompat gestureDetectorCompat;
    private Adapter mAdapter;

    public HomeFragment() {

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
        gestureDetectorCompat = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float x = e.getX();
                float y = e.getY();
                View child = mRecyclerView.findChildViewUnder(x, y);
                if (null == child) return false;
                int position = mRecyclerView.getChildLayoutPosition(child);
                RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(child);
                Intent intent = new Intent(getContext(), ShopDetailActivity.class);
                startActivity(intent);
                return true;
            }
        });
        mAdapter = new Adapter(mRecommends);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                gestureDetectorCompat.onTouchEvent(e);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                gestureDetectorCompat.onTouchEvent(e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
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
        NetworkManager.getInstance().getRecommend(subscriber);
    }

    /**
     * 获取滚动条
     */
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
        NetworkManager.getInstance().getBanner(subscriber);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mSubscriptions) {
            mSubscriptions.unsubscribe();
        }
    }


}
