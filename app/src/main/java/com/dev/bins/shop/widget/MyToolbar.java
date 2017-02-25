package com.dev.bins.shop.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.bins.shop.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by bin on 24/02/2017.
 */

public class MyToolbar extends Toolbar {
    @BindView(R.id.et_search)
    EditText mSearchEditText;
    @BindView(R.id.tv_title)
    TextView mTitleText;
    @BindView(R.id.btn_right)
    Button mRightButton;
    private View mView;

    public MyToolbar(Context context) {
        this(context, null);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyToolbar, defStyleAttr, 0);
        Drawable drawable = typedArray.getDrawable(R.styleable.MyToolbar_right_img);

        if (drawable != null) {
            mRightButton.setBackground(drawable);
            mRightButton.setVisibility(VISIBLE);
        }

        typedArray.recycle();

    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.my_toolbar, null);
        ButterKnife.bind(this, mView);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_VERTICAL);
        addView(mView, params);

    }

    public void setRightButtonClickListener(OnClickListener onClickListener) {
        mRightButton.setOnClickListener(onClickListener);
    }


    @Override
    public void setTitle(@StringRes int resId) {
        if (null == mTitleText) init();
        mTitleText.setVisibility(VISIBLE);
        mSearchEditText.setVisibility(GONE);
        mTitleText.setText(getContext().getText(resId));
    }


    @Override
    public void setTitle(CharSequence title) {
        if (null == mTitleText) init();
        mTitleText.setVisibility(VISIBLE);
        mTitleText.setText(title);
    }

    public void setRightButtonText(String text) {
        if (null != mRightButton) {
            mRightButton.setVisibility(VISIBLE);
            mRightButton.setText(text);
            mSearchEditText.setVisibility(GONE);
        }
    }


    public void showTitle() {
        if (null != mTitleText) {
            mTitleText.setVisibility(VISIBLE);
        }
    }


    public void hideTitle() {
        if (null != mTitleText) {
            mTitleText.setVisibility(GONE);
        }
    }
}
