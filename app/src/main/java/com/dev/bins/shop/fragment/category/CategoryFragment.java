package com.dev.bins.shop.fragment.category;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;
import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.Category;
import com.dev.bins.shop.fragment.BaseFragment;
import com.dev.bins.shop.net.NetworkManager;
import com.dev.bins.shop.widget.MyToolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.Subscription;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {

    List<Category> mCategories = new ArrayList<>();

    @BindView(R.id.slider)
    SliderLayout mSliderLayout;
    @BindView(R.id.category_toolbar)
    MyToolbar mToolbar;
    @BindView(R.id.item)
    RecyclerView mItemRecyclerView;
    @BindView(R.id.content)
    RecyclerView mContentRecyclerView;


    CategoryItemAdapter mItemAdapter;
    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initItemRecycler(view);
    }

    private void initItemRecycler(View view) {
        mItemAdapter = new CategoryItemAdapter(mCategories);
        mItemRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        mItemRecyclerView.setAdapter(mItemAdapter);
        Subscriber<List<Category>> subscriber = new Subscriber<List<Category>>() {
            @Override
            public void onCompleted() {
                mItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Category> category) {
                mCategories.clear();
                mCategories.addAll(category);
            }
        };
        Subscription subscription = NetworkManager.getInstance().getCategory(subscriber);
        mSubscriptions.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mSubscriptions){
            mSubscriptions.unsubscribe();
        }
    }
}
