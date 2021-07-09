package com.android.webview.chromium;

import java.util.function.Supplier;

final class Lazy<T> {

    final Supplier<T> mSupplier;
    T mValue;

    Lazy(Supplier<T> supplier) {
        mSupplier = supplier;
    }

    final T get() {
        if (mValue == null) mValue = mSupplier.get();
        return mValue;
    }
}
