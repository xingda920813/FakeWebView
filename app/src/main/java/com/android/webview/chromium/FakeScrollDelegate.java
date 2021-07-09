package com.android.webview.chromium;

import android.webkit.WebViewProvider;

public class FakeScrollDelegate implements WebViewProvider.ScrollDelegate {

    @Override
    public int computeHorizontalScrollRange() {
        return 0;
    }

    @Override
    public int computeHorizontalScrollOffset() {
        return 0;
    }

    @Override
    public int computeVerticalScrollRange() {
        return 0;
    }

    @Override
    public int computeVerticalScrollOffset() {
        return 0;
    }

    @Override
    public int computeVerticalScrollExtent() {
        return 0;
    }

    @Override
    public void computeScroll() {

    }
}
