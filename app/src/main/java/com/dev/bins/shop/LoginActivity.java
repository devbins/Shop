package com.dev.bins.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.bins.shop.bean.BaseBean;
import com.dev.bins.shop.net.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;


public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.et_phone)
    EditText mEditTextPhone;
    @BindView(R.id.et_pwd)
    EditText mEditTextPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.tv_regist)
    TextView mTextViewRegist;
    @BindView(R.id.tv_forget_pwd)
    TextView mTextViewForgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_login)
    public void login(View view) {
        boolean hasEmpty = false;
        if (TextUtils.isEmpty(mEditTextPhone.getText().toString().trim())) {
            mEditTextPhone.setError(getString(R.string.error_empty));
            hasEmpty = true;
        }
        if (TextUtils.isEmpty(mEditTextPwd.getText().toString().trim())) {
            mEditTextPwd.setError(getString(R.string.error_empty));
            hasEmpty = true;
        }
        if (!hasEmpty) {
            Subscriber<BaseBean> subscriber = new Subscriber<BaseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(BaseBean baseBean) {
                    int errorCode = baseBean.getErrorCode();
                    
                }
            };
            NetworkManager.getInstance()
                    .login(subscriber, mEditTextPhone.getText().toString().trim(),
                            mEditTextPwd.getText().toString().trim());
        }

    }

    @OnClick(R.id.tv_forget_pwd)
    public void forgetPwd(View view) {

    }

    @OnClick(R.id.tv_regist)
    public void regist(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}

