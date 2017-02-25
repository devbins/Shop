package com.dev.bins.shop.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.bins.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bin on 25/02/2017.
 */

public class CountView extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_del)
    Button btnDel;
    private View mView;

    private int mCount = 1;

    public CountView(Context context) {
        this(context, null);
    }

    public CountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = View.inflate(context, R.layout.countview, this);
        ButterKnife.bind(this, mView);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                mCount++;
                break;
            case R.id.btn_del:
                mCount--;
                break;
        }
        tvCount.setText(String.valueOf(mCount));
    }


    public int getmCount() {
        return mCount;
    }

    public void setmCount(int count) {
        this.mCount = count;
        tvCount.setText(String.valueOf(count));
    }
}
