package com.android.webview.chromium;

import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;

@SuppressWarnings({"deprecation", "unused"})
public class FakeCookieManager extends CookieManager {

    @Override
    public void setAcceptCookie(boolean accept) {

    }

    @Override
    public boolean acceptCookie() {
        return false;
    }

    @Override
    public void setAcceptThirdPartyCookies(WebView webview, boolean accept) {

    }

    @Override
    public boolean acceptThirdPartyCookies(WebView webview) {
        return false;
    }

    @Override
    public void setCookie(String url, String value) {

    }

    @Override
    public void setCookie(String url, String value, ValueCallback<Boolean> callback) {

    }

    @Override
    public String getCookie(String url) {
        return "";
    }

    public String getCookie(String url, boolean privateBrowsing) {
        return "";
    }

    @Override
    public void removeSessionCookie() {

    }

    @Override
    public void removeSessionCookies(ValueCallback<Boolean> callback) {

    }

    @Override
    public void removeAllCookie() {

    }

    @Override
    public void removeAllCookies(ValueCallback<Boolean> callback) {

    }

    @Override
    public boolean hasCookies() {
        return false;
    }

    public boolean hasCookies(boolean privateBrowsing) {
        return false;
    }

    @Override
    public void removeExpiredCookie() {

    }

    @Override
    public void flush() {

    }

    protected boolean allowFileSchemeCookiesImpl() {
        return false;
    }

    protected void setAcceptFileSchemeCookiesImpl(boolean accept) {

    }
}
