package com.android.webview.chromium;

import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebSettings;

public class FakeServiceWorkerWebSettings extends ServiceWorkerWebSettings {

    @Override
    public void setCacheMode(int mode) {

    }

    @Override
    public int getCacheMode() {
        return WebSettings.LOAD_DEFAULT;
    }

    @Override
    public void setAllowContentAccess(boolean allow) {

    }

    @Override
    public boolean getAllowContentAccess() {
        return false;
    }

    @Override
    public void setAllowFileAccess(boolean allow) {

    }

    @Override
    public boolean getAllowFileAccess() {
        return false;
    }

    @Override
    public void setBlockNetworkLoads(boolean flag) {

    }

    @Override
    public boolean getBlockNetworkLoads() {
        return false;
    }
}
