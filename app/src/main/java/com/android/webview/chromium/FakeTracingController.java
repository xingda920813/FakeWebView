package com.android.webview.chromium;

import android.annotation.SuppressLint;
import android.webkit.TracingConfig;
import android.webkit.TracingController;

import java.io.OutputStream;
import java.util.concurrent.Executor;

@SuppressLint("NewApi")
@SuppressWarnings("deprecation")
public class FakeTracingController extends TracingController {

    @Override
    public void start(TracingConfig tracingConfig) {

    }

    @Override
    public boolean stop(OutputStream outputStream, Executor executor) {
        return false;
    }

    @Override
    public boolean isTracing() {
        return false;
    }
}
