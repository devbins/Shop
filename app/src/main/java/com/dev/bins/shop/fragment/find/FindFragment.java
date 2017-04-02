package com.dev.bins.shop.fragment.find;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.dev.bins.shop.R;
import com.dev.bins.shop.bean.Goods;
import com.dev.bins.shop.bean.GoodsItem;
import com.dev.bins.shop.bean.ResponseMsg;
import com.dev.bins.shop.fragment.BaseFragment;
import com.dev.bins.shop.net.NetworkManager;
import com.dev.bins.shop.ui.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.hot_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    FindAdapter mAdapter;
    GestureDetector mGestureDetector;
    private List<GoodsItem> mGoods = new ArrayList<>();
    private int curPage = 1;

    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance() {

        Bundle args = new Bundle();

        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipe.setOnRefreshListener(this);
        mAdapter = new FindAdapter(mGoods);
        mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                float x = e.getX();
                float y = e.getY();
                View viewUnder = mRecyclerView.findChildViewUnder(x, y);
                if (null != viewUnder) {
                    int position = mRecyclerView.getChildLayoutPosition(viewUnder);
                    if (-1 != position) {
                        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
                        startActivity(intent);
                    }
                }
                return super.onSingleTapUp(e);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                mGestureDetector.onTouchEvent(e);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                mGestureDetector.onTouchEvent(e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        load(1);
    }

    public void load(int page) {
        Subscriber<ResponseMsg<Goods>> subscriber = new Subscriber<ResponseMsg<Goods>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mSwipe.setRefreshing(false);
                e.printStackTrace();
            }

            @Override
            public void onNext(ResponseMsg<Goods> goodsResponseMsg) {
                mGoods.clear();
                mGoods.addAll(goodsResponseMsg.getData().getGoods());
                mAdapter.notifyDataSetChanged();
                mSwipe.setRefreshing(false);
            }
        };
        NetworkManager.getInstance().getGoods(subscriber, page, 10);
//        mSubscriptions.add(subscription);

    }


    @Override
    public void onRefresh() {
        load(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mSubscriptions) {
            mSubscriptions.unsubscribe();
        }
    }
}
