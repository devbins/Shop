package com.dev.bins.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopDetailActivity extends AppCompatActivity {


    @BindView(R.id.webview)
    WebView mWebView;

    @BindView(R.id.js)
    Button btnJs;
    private JS js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        String id = getIntent().getStringExtra("id");

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        js = new JS();
        mWebView.addJavascriptInterface(js, "app");
        mWebView.loadUrl("file:///android_asset/index.html");
        btnJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                js.changeHtml();
            }
        });
    }


    class JS {
        @JavascriptInterface
        public void show() {
            Toast.makeText(ShopDetailActivity.this, "form html", Toast.LENGTH_SHORT).show();
        }

        @JavascriptInterface
        public void changeHtml() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    需要在主线程执行
                    mWebView.loadUrl("javascript:change()");

                }
            });
        }

    }
}
