package com.android.webview.chromium;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ServiceWorkerController;
import android.webkit.TokenBindingService;
import android.webkit.TracingController;
import android.webkit.WebIconDatabase;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewDatabase;
import android.webkit.WebViewDelegate;
import android.webkit.WebViewFactoryProvider;
import android.webkit.WebViewProvider;
import android.widget.FrameLayout;

import java.lang.reflect.Field;

@SuppressWarnings({"unused", "deprecation", "JavaReflectionMemberAccess"})
@SuppressLint("SoonBlockedPrivateApi")
public class WebViewChromiumFactoryProvider implements WebViewFactoryProvider {

    final Lazy<Field> mField = new Lazy<>(() -> {
        try {
            final Field f = View.class.getDeclaredField("mLayoutParams");
            f.setAccessible(true);
            return f;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    });

    final Lazy<FrameLayout.LayoutParams> mLayoutParams = new Lazy<>(() -> new FrameLayout
            .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER));

    public WebViewChromiumFactoryProvider(WebViewDelegate delegate) {

    }

    @Override
    public Statics getStatics() {
        return new FakeStatics();
    }

    @Override
    public WebViewProvider createWebView(WebView webView, WebView.PrivateAccess privateAccess) {
        if (mField.get() != null) {
            try {
                mField.get().set(webView, mLayoutParams.get());
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
        return new FakeWebViewProvider(webView);
    }

    @Override
    public GeolocationPermissions getGeolocationPermissions() {
        return GeolocationPermissions.getInstance();
    }

    @Override
    public CookieManager getCookieManager() {
        return new FakeCookieManager();
    }

    @Override
    public TokenBindingService getTokenBindingService() {
        return null;
    }

    @Override
    public TracingController getTracingController() {
        return new FakeTracingController();
    }

    @Override
    public ServiceWorkerController getServiceWorkerController() {
        return new FakeServiceWorkerController();
    }

    @Override
    public WebIconDatabase getWebIconDatabase() {
        return new FakeWebIconDatabase();
    }

    @Override
    public WebStorage getWebStorage() {
        return WebStorage.getInstance();
    }

    @Override
    public WebViewDatabase getWebViewDatabase(Context context) {
        return new FakeWebViewDatabase();
    }

    @Override
    public ClassLoader getWebViewClassLoader() {
        return getClass().getClassLoader();
    }
}
