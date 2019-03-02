package com.hie2j.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView my_WebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_WebView = findViewById(R.id.myWebView);
        //加载网址
//        my_WebView.loadUrl("http://www.baidu.com");
//        my_WebView.loadUrl("http://gdcp.fanya.chaoxing.com/portal");
        //加载assets目录下的本地网页
//        my_WebView.loadUrl("file:///android_asset/test.html");
        //加载本地网页内容
        /*String html = "<html lang=\"en\">\n" +
                "\t<meta charset=\"utf-8\">\n" +
                "\t<title>本地网页</title>\n" +
                "\t<body>\n" +
                "\t\t<div id=\"app\">\n" +
                "\t\t\thello world<br>\n" +
                "\t\t\t<img src=\"file:///android_asset/a.jpg\">\n" +
                "\t\t</div>\n" +
                "\t</body>\n" +
                "</html>";
        my_WebView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);*/
        //加载sd卡内的网页
        my_WebView.loadUrl("file:///mnt/shared/Other/test.html");

        //防止跳转打开系统浏览器
        my_WebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("MainActivity","shouldOverrideUrlLoading");
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("MainActivity","onPageStarted");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("MainActivity","onPageFinished");
                super.onPageFinished(view, url);
            }
        });
        findViewById(R.id.go_forward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivity","onGoForward");
                if (my_WebView.canGoForward()){
                    my_WebView.goForward();
                }
            }
        });

        WebSettings webSettings = my_WebView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
    }

    @Override
    public void onBackPressed() {
        Log.e("MainActivity","onBackPressed");
        if (my_WebView.canGoBack()){
            my_WebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}

