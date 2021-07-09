package com.android.webview.chromium;

import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.ServiceWorkerWebSettings;

@SuppressWarnings("deprecation")
public class FakeServiceWorkerController extends ServiceWorkerController {

    @Override
    public ServiceWorkerWebSettings getServiceWorkerWebSettings() {
        return new FakeServiceWorkerWebSettings();
    }

    @Override
    public void setServiceWorkerClient(ServiceWorkerClient client) {

    }
}
