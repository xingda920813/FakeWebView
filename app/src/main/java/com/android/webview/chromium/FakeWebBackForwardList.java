package com.android.webview.chromium;

import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;

@SuppressWarnings("NullableProblems")
public class FakeWebBackForwardList extends WebBackForwardList {

    @Override
    public WebHistoryItem getCurrentItem() {
        return new FakeWebHistoryItem();
    }

    @Override
    public int getCurrentIndex() {
        return 0;
    }

    @Override
    public WebHistoryItem getItemAtIndex(int index) {
        return new FakeWebHistoryItem();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    protected WebBackForwardList clone() {
        return this;
    }
}
