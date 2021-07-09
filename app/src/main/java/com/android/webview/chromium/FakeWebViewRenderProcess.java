package com.android.webview.chromium;

import android.annotation.SuppressLint;
import android.webkit.WebViewRenderProcess;

@SuppressLint("NewApi")
public class FakeWebViewRenderProcess extends WebViewRenderProcess {

    @Override
    public boolean terminate() {
        return false;
    }
}
