package com.example.taipeizoo.Util;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taipeizoo.R;

public class WebViewActivity extends AppCompatActivity {

    private String mUrlString;
    WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);
        mWebView = findViewById(R.id.mWebView);
        getBundle();
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(mUrlString);

    }

    private void getBundle() {
        Bundle iBundle = getIntent().getExtras();
        mUrlString = iBundle.getString("getUrl");
    }


}
