package com.android.webview.chromium;

import android.annotation.SuppressLint;
import android.webkit.WebView;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;

@SuppressLint("NewApi")
public class FakeWebViewRenderProcessClient extends WebViewRenderProcessClient {

    @Override
    public void onRenderProcessUnresponsive(WebView view, WebViewRenderProcess renderer) {

    }

    @Override
    public void onRenderProcessResponsive(WebView view, WebViewRenderProcess renderer) {

    }
}
