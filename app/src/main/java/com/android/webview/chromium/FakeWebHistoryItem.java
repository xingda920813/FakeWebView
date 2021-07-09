package com.android.webview.chromium;

import android.graphics.Bitmap;
import android.webkit.WebHistoryItem;

@SuppressWarnings({"NullableProblems", "unused"})
public class FakeWebHistoryItem extends WebHistoryItem {

    public int getId() {
        return 0;
    }

    @Override
    public String getUrl() {
        return "";
    }

    @Override
    public String getOriginalUrl() {
        return "";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public Bitmap getFavicon() {
        return null;
    }

    @Override
    protected WebHistoryItem clone() {
        return this;
    }
}
