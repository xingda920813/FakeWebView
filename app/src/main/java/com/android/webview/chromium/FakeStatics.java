package com.android.webview.chromium;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebViewFactoryProvider;

import java.util.List;

public class FakeStatics implements WebViewFactoryProvider.Statics {

    @Override
    public String findAddress(String addr) {
        return "";
    }

    @Override
    public String getDefaultUserAgent(Context context) {
        return "";
    }

    @Override
    public void freeMemoryForTests() {

    }

    @Override
    public void setWebContentsDebuggingEnabled(boolean enable) {

    }

    @Override
    public void clearClientCertPreferences(Runnable onCleared) {

    }

    @Override
    public void enableSlowWholeDocumentDraw() {

    }

    @Override
    public Uri[] parseFileChooserResult(int resultCode, Intent intent) {
        return new Uri[0];
    }

    @Override
    public void initSafeBrowsing(Context context, ValueCallback<Boolean> callback) {

    }

    @Override
    public void setSafeBrowsingWhitelist(List<String> hosts, ValueCallback<Boolean> callback) {

    }

    @Override
    public Uri getSafeBrowsingPrivacyPolicyUrl() {
        return Uri.EMPTY;
    }
}
