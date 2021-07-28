package com.android.webview.chromium;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

@SuppressWarnings({"unused", "deprecation", "JavaReflectionMemberAccess"})
@SuppressLint("SoonBlockedPrivateApi")
public class WebViewChromiumFactoryProvider implements WebViewFactoryProvider {

    final Lazy<Field> mField = new Lazy<>(() -> {
        try {
            final var f = View.class.getDeclaredField("mLayoutParams");
            f.setAccessible(true);
            return f;
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    });

    final Lazy<LinearLayout.LayoutParams> mLinearLayoutParams = new Lazy<>(() -> new LinearLayout
            .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    final Lazy<RelativeLayout.LayoutParams> mRelativeLayoutParams = new Lazy<>(() -> new RelativeLayout
            .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

    final Lazy<FrameLayout.LayoutParams> mFrameLayoutParams = new Lazy<>(() -> new FrameLayout
            .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER));

    public WebViewChromiumFactoryProvider(WebViewDelegate delegate) {

    }

    @Override
    public Statics getStatics() {
        return new FakeStatics();
    }

    void setLayoutParams(WebView webView) {
        if (mField.get() == null) return;
        final var parent = webView.getParent();
        final ViewGroup.MarginLayoutParams layoutParams;
        if (parent instanceof LinearLayout) {
            layoutParams = mLinearLayoutParams.get();
        } else if (parent instanceof RelativeLayout) {
            layoutParams = mRelativeLayoutParams.get();
        } else {
            layoutParams = mFrameLayoutParams.get();
        }
        try {
            mField.get().set(webView, layoutParams);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WebViewProvider createWebView(WebView webView, WebView.PrivateAccess privateAccess) {
        webView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                webView.removeOnAttachStateChangeListener(this);
                setLayoutParams(webView);
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                webView.removeOnAttachStateChangeListener(this);
            }
        });
        setLayoutParams(webView);
        webView.setVisibility(View.GONE);
        return new FakeWebViewProvider(webView);
    }

    @Override
    public GeolocationPermissions getGeolocationPermissions() {
        return new GeolocationPermissions();
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
        return new WebStorage();
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
