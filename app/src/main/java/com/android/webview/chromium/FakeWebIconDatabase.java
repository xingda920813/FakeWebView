package com.android.webview.chromium;

import android.content.ContentResolver;
import android.webkit.WebIconDatabase;

@SuppressWarnings({"deprecation", "unused"})
public class FakeWebIconDatabase extends WebIconDatabase {

    @Override
    public void open(String path) {

    }

    @Override
    public void close() {

    }

    @Override
    public void removeAllIcons() {

    }

    @Override
    public void requestIconForPageUrl(String url, IconListener listener) {

    }

    public void bulkRequestIconForPageUrl(ContentResolver cr, String where, IconListener listener) {

    }

    @Override
    public void retainIconForPageUrl(String url) {

    }

    @Override
    public void releaseIconForPageUrl(String url) {

    }
}
