package com.android.webview.chromium;

import android.webkit.WebViewDatabase;

@SuppressWarnings("deprecation")
public class FakeWebViewDatabase extends WebViewDatabase {

    @Override
    public boolean hasUsernamePassword() {
        return false;
    }

    @Override
    public void clearUsernamePassword() {

    }

    @Override
    public boolean hasHttpAuthUsernamePassword() {
        return false;
    }

    @Override
    public void clearHttpAuthUsernamePassword() {

    }

    @Override
    public void setHttpAuthUsernamePassword(String host, String realm, String username, String password) {

    }

    @Override
    public String[] getHttpAuthUsernamePassword(String host, String realm) {
        return new String[0];
    }

    @Override
    public boolean hasFormData() {
        return false;
    }

    @Override
    public void clearFormData() {

    }
}
