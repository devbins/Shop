package com.dev.bins.shop.fragment.me;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.bins.shop.AddAddressActivity;
import com.dev.bins.shop.OrderActivity;
import com.dev.bins.shop.R;
import com.dev.bins.shop.fragment.BaseFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.me_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_order)
    TextView mTvOrder;

    @BindView(R.id.iv_bg)
    ImageView mImageViewBg;

    @BindView(R.id.collapsingtoolbarlayout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.avatar)
    ImageView mCircleImageView;

    public MeFragment() {
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
        mTvOrder.setOnClickListener(this);
        mTvAddress.setOnClickListener(this);
        mCollapsingToolbarLayout.setTitle(getString(R.string.me));
        Picasso.with(getContext())
                .load(R.drawable.avatar_bg)
        .transform(new BlurTransformation(getContext()))
                .into(mImageViewBg);
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

    public class BlurTransformation implements Transformation {

        RenderScript rs;

        public BlurTransformation(Context context) {
            super();
            rs = RenderScript.create(context);
        }

        @SuppressLint("NewApi")
        @Override
        public Bitmap transform(Bitmap bitmap) {
            // 创建一个Bitmap作为最后处理的效果Bitmap
            Bitmap blurredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

            // 分配内存
            Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
            Allocation output = Allocation.createTyped(rs, input.getType());

            // 根据我们想使用的配置加载一个实例
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // 设置模糊半径
            script.setRadius(10);

            //开始操作
            script.forEach(output);

            // 将结果copy到blurredBitmap中
            output.copyTo(blurredBitmap);

            //释放资源
            bitmap.recycle();

            return blurredBitmap;
        }

        @Override
        public String key() {
            return "blur";
        }
    }
}
