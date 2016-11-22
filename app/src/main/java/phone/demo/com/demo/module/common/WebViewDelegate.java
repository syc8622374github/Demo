package phone.demo.com.demo.module.common;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import phone.demo.com.demo.R;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/11/22 0022.
 */

public class WebViewDelegate extends AppDelegate {

    private WebView webView;
    private ProgressBar progressBar;

    protected WebViewDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        webView = get(R.id.web_view);
        progressBar = get(R.id.pb_progress);
        initWebSetting();
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                activity.setTitle(title);
            }
        });

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initWebSetting() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);// 支持JS
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setUseWideViewPort(true);// 支持双击放大缩小
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_web;
    }
}
