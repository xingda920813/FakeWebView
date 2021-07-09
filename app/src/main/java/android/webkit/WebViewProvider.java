/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.webkit;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.print.PrintDocumentAdapter;
import android.util.SparseArray;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebView.PictureListener;
import android.webkit.WebView.VisualStateCallback;

import java.io.BufferedWriter;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * WebView backend provider interface: this interface is the abstract backend to a WebView
 * instance; each WebView object is bound to exactly one WebViewProvider object which implements
 * the runtime behavior of that WebView.
 *
 * All methods must behave as per their namesake in {@link WebView}, unless otherwise noted.
 */
@SuppressWarnings({"unused", "deprecation"})
public interface WebViewProvider {

    //-------------------------------------------------------------------------
    // Main interface for backend provider of the WebView class.
    //-------------------------------------------------------------------------
    /**
     * Initialize this WebViewProvider instance. Called after the WebView has fully constructed.
     * @param javaScriptInterfaces is a Map of interface names, as keys, and
     * object implementing those interfaces, as values.
     * @param privateBrowsing If {@code true} the web view will be initialized in private /
     * incognito mode.
     */
    void init(Map<String, Object> javaScriptInterfaces,
              boolean privateBrowsing);

    // Deprecated - should never be called
    void setHorizontalScrollbarOverlay(boolean overlay);

    // Deprecated - should never be called
    void setVerticalScrollbarOverlay(boolean overlay);

    // Deprecated - should never be called
    boolean overlayHorizontalScrollbar();

    // Deprecated - should never be called
    boolean overlayVerticalScrollbar();

    int getVisibleTitleHeight();

    SslCertificate getCertificate();

    void setCertificate(SslCertificate certificate);

    void savePassword(String host, String username, String password);

    void setHttpAuthUsernamePassword(String host, String realm,
                                     String username, String password);

    String[] getHttpAuthUsernamePassword(String host, String realm);

    /**
     * See {@link WebView#destroy()}.
     * As well as releasing the internal state and resources held by the implementation,
     * the provider should null all references it holds on the WebView proxy class, and ensure
     * no further method calls are made to it.
     */
    void destroy();

    void setNetworkAvailable(boolean networkUp);

    WebBackForwardList saveState(Bundle outState);

    boolean savePicture(Bundle b, final File dest);

    boolean restorePicture(Bundle b, File src);

    WebBackForwardList restoreState(Bundle inState);

    void loadUrl(String url, Map<String, String> additionalHttpHeaders);

    void loadUrl(String url);

    void postUrl(String url, byte[] postData);

    void loadData(String data, String mimeType, String encoding);

    void loadDataWithBaseURL(String baseUrl, String data,
                             String mimeType, String encoding, String historyUrl);

    void evaluateJavaScript(String script, ValueCallback<String> resultCallback);

    void saveWebArchive(String filename);

    void saveWebArchive(String basename, boolean autoname, ValueCallback<String> callback);

    void stopLoading();

    void reload();

    boolean canGoBack();

    void goBack();

    boolean canGoForward();

    void goForward();

    boolean canGoBackOrForward(int steps);

    void goBackOrForward(int steps);

    boolean isPrivateBrowsingEnabled();

    boolean pageUp(boolean top);

    boolean pageDown(boolean bottom);

    void insertVisualStateCallback(long requestId, VisualStateCallback callback);

    void clearView();

    Picture capturePicture();

    PrintDocumentAdapter createPrintDocumentAdapter(String documentName);

    float getScale();

    void setInitialScale(int scaleInPercent);

    void invokeZoomPicker();

    HitTestResult getHitTestResult();

    void requestFocusNodeHref(Message hrefMsg);

    void requestImageRef(Message msg);

    String getUrl();

    String getOriginalUrl();

    String getTitle();

    Bitmap getFavicon();

    String getTouchIconUrl();

    int getProgress();

    int getContentHeight();

    int getContentWidth();

    void pauseTimers();

    void resumeTimers();

    void onPause();

    void onResume();

    boolean isPaused();

    void freeMemory();

    void clearCache(boolean includeDiskFiles);

    void clearFormData();

    void clearHistory();

    void clearSslPreferences();

    WebBackForwardList copyBackForwardList();

    void setFindListener(WebView.FindListener listener);

    void findNext(boolean forward);

    int findAll(String find);

    void findAllAsync(String find);

    boolean showFindDialog(String text, boolean showIme);

    void clearMatches();

    void documentHasImages(Message response);

    void setWebViewClient(WebViewClient client);

    WebViewClient getWebViewClient();

    WebViewRenderProcess getWebViewRenderProcess();

    void setWebViewRenderProcessClient(
            Executor executor,
            WebViewRenderProcessClient client);

    WebViewRenderProcessClient getWebViewRenderProcessClient();

    void setDownloadListener(DownloadListener listener);

    void setWebChromeClient(WebChromeClient client);

    WebChromeClient getWebChromeClient();

    void setPictureListener(PictureListener listener);

    void addJavascriptInterface(Object obj, String interfaceName);

    void removeJavascriptInterface(String interfaceName);

    WebMessagePort[] createWebMessageChannel();

    void postMessageToMainFrame(WebMessage message, Uri targetOrigin);

    WebSettings getSettings();

    void setMapTrackballToArrowKeys(boolean setMap);

    void flingScroll(int vx, int vy);

    View getZoomControls();

    boolean canZoomIn();

    boolean canZoomOut();

    boolean zoomBy(float zoomFactor);

    boolean zoomIn();

    boolean zoomOut();

    void dumpViewHierarchyWithProperties(BufferedWriter out, int level);

    View findHierarchyView(String className, int hashCode);

    void setRendererPriorityPolicy(int rendererRequestedPriority, boolean waivedWhenNotVisible);

    int getRendererRequestedPriority();

    boolean getRendererPriorityWaivedWhenNotVisible();

    @SuppressWarnings("unused")
    default void setTextClassifier(TextClassifier textClassifier) {}

    @SuppressLint("NewApi")
    default TextClassifier getTextClassifier() { return TextClassifier.NO_OP; }

    //-------------------------------------------------------------------------
    // Provider internal methods
    //-------------------------------------------------------------------------

    /**
     * @return the ViewDelegate implementation. This provides the functionality to back all of
     * the name-sake functions from the View and ViewGroup base classes of WebView.
     */
    /* package */ ViewDelegate getViewDelegate();

    /**
     * @return a ScrollDelegate implementation. Normally this would be same object as is
     * returned by getViewDelegate().
     */
    /* package */ ScrollDelegate getScrollDelegate();

    /**
     * Only used by FindActionModeCallback to inform providers that the find dialog has
     * been dismissed.
     */
    void notifyFindDialogDismissed();

    //-------------------------------------------------------------------------
    // View / ViewGroup delegation methods
    //-------------------------------------------------------------------------

    /**
     * Provides mechanism for the name-sake methods declared in View and ViewGroup to be delegated
     * into the WebViewProvider instance.
     * NOTE: For many of these methods, the WebView will provide a super.Foo() call before or after
     * making the call into the provider instance. This is done for convenience in the common case
     * of maintaining backward compatibility. For remaining super class calls (e.g. where the
     * provider may need to only conditionally make the call based on some internal state) see the
     * WebView.PrivateAccess callback class.
     */
    // TODO: See if the pattern of the super-class calls can be rationalized at all, and document
    // the remainder on the methods below.
    interface ViewDelegate {

        boolean shouldDelayChildPressedState();

        void onProvideVirtualStructure(android.view.ViewStructure structure);

        default void onProvideAutofillVirtualStructure(
                @SuppressWarnings("unused") android.view.ViewStructure structure,
                @SuppressWarnings("unused") int flags) {
        }

        default void autofill(@SuppressWarnings("unused") SparseArray<AutofillValue> values) {
        }

        default boolean isVisibleToUserForAutofill(@SuppressWarnings("unused") int virtualId) {
            return true; // true is the default value returned by View.isVisibleToUserForAutofill()
        }

        default void onProvideContentCaptureStructure(
                @SuppressWarnings("unused") android.view.ViewStructure structure,
                @SuppressWarnings("unused") int flags) {
        }

        AccessibilityNodeProvider getAccessibilityNodeProvider();

        void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info);

        void onInitializeAccessibilityEvent(AccessibilityEvent event);

        boolean performAccessibilityAction(int action, Bundle arguments);

        void setOverScrollMode(int mode);

        void setScrollBarStyle(int style);

        void onDrawVerticalScrollBar(Canvas canvas, Drawable scrollBar, int l, int t,
                                     int r, int b);

        void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY);

        void onWindowVisibilityChanged(int visibility);

        void onDraw(Canvas canvas);

        void setLayoutParams(LayoutParams layoutParams);

        boolean performLongClick();

        void onConfigurationChanged(Configuration newConfig);

        InputConnection onCreateInputConnection(EditorInfo outAttrs);

        boolean onDragEvent(DragEvent event);

        boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event);

        boolean onKeyDown(int keyCode, KeyEvent event);

        boolean onKeyUp(int keyCode, KeyEvent event);

        void onAttachedToWindow();

        void onDetachedFromWindow();

        default void onMovedToDisplay(int displayId, Configuration config) {}

        void onVisibilityChanged(View changedView, int visibility);

        void onWindowFocusChanged(boolean hasWindowFocus);

        void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect);

        boolean setFrame(int left, int top, int right, int bottom);

        void onSizeChanged(int w, int h, int ow, int oh);

        void onScrollChanged(int l, int t, int oldl, int oldt);

        boolean dispatchKeyEvent(KeyEvent event);

        boolean onTouchEvent(MotionEvent ev);

        boolean onHoverEvent(MotionEvent event);

        boolean onGenericMotionEvent(MotionEvent event);

        boolean onTrackballEvent(MotionEvent ev);

        boolean requestFocus(int direction, Rect previouslyFocusedRect);

        void onMeasure(int widthMeasureSpec, int heightMeasureSpec);

        boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate);

        void setBackgroundColor(int color);

        void setLayerType(int layerType, Paint paint);

        void preDispatchDraw(Canvas canvas);

        void onStartTemporaryDetach();

        void onFinishTemporaryDetach();

        void onActivityResult(int requestCode, int resultCode, Intent data);

        Handler getHandler(Handler originalHandler);

        View findFocus(View originalFocusedView);

        @SuppressWarnings("unused")
        default boolean onCheckIsTextEditor() {
            return false;
        }
    }

    interface ScrollDelegate {

        // These methods are declared protected in the ViewGroup base class. This interface
        // exists to promote them to public so they may be called by the WebView proxy class.
        // TODO: Combine into ViewDelegate?
        /**
         * See {@link WebView#computeHorizontalScrollRange}
         */
        int computeHorizontalScrollRange();

        /**
         * See {@link WebView#computeHorizontalScrollOffset}
         */
        int computeHorizontalScrollOffset();

        /**
         * See {@link WebView#computeVerticalScrollRange}
         */
        int computeVerticalScrollRange();

        /**
         * See {@link WebView#computeVerticalScrollOffset}
         */
        int computeVerticalScrollOffset();

        /**
         * See {@link WebView#computeVerticalScrollExtent}
         */
        int computeVerticalScrollExtent();

        /**
         * See {@link WebView#computeScroll}
         */
        void computeScroll();
    }
}
