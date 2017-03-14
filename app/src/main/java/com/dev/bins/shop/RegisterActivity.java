package com.dev.bins.shop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.bins.shop.bean.BaseBean;
import com.dev.bins.shop.bean.ResponseMsg;
import com.dev.bins.shop.bean.User;
import com.dev.bins.shop.net.NetworkManager;
import com.dev.bins.shop.util.UserManager;

import java.util.HashSet;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_phone)
    EditText mEditTextPhone;
    @BindView(R.id.et_pwd)
    EditText mEditTextPwd;
    private Pattern mPattern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mPattern = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
    }

    @OnClick(R.id.btn_reg)
    public void reg(View view) {
        boolean hasEmpty = false;
        if (TextUtils.isEmpty(mEditTextPhone.getText().toString().trim())) {
            mEditTextPhone.setError(getString(R.string.error_empty));
            hasEmpty = true;
        }

        if (!mPattern.matcher(mEditTextPhone.getText().toString().trim()).matches()) {
            mEditTextPhone.setError(getString(R.string.error_phone));
            return;
        }

        if (TextUtils.isEmpty(mEditTextPwd.getText().toString().trim())) {
            mEditTextPwd.setError(getString(R.string.error_empty));
            hasEmpty = true;
        }

        if (!hasEmpty) {
            Subscriber<ResponseMsg<User>> subscriber = new Subscriber<ResponseMsg<User>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ResponseMsg<User> userResponseMsg) {
                    int errorCode = userResponseMsg.getErrorCode();
                    if (0 == errorCode) {
                        App.getInstance().setmUser(userResponseMsg.getData());
                        UserManager.save(getApplicationContext(), userResponseMsg.getData());
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, getString(R.string.reg_error), Toast.LENGTH_SHORT).show();
                    }
                }
            };

            NetworkManager.getInstance()
                    .reg(subscriber, mEditTextPhone.getText().toString().trim()
                            , mEditTextPwd.getText().toString().trim());
        }

    }


}
