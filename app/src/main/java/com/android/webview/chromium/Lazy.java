package com.android.webview.chromium;

import java.util.function.Supplier;

public final class Lazy<T> {

    private final Supplier<T> mSupplier;
    private volatile T mValue;

    public Lazy(Supplier<T> supplier) {
        mSupplier = supplier;
    }

    public T getOrCompute() {
        final T result = mValue;
        return result == null ? maybeCompute() : result;
    }

    private synchronized T maybeCompute() {
        if (mValue == null) {
            mValue = mSupplier.get();
        }
        return mValue;
    }
}
