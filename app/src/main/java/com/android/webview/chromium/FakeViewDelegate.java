package com.android.webview.chromium;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.webkit.WebViewProvider;

public class FakeViewDelegate implements WebViewProvider.ViewDelegate {

    private final Lazy<Handler> mHandler = new Lazy<>(() -> new Handler(Looper.getMainLooper()));

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    public void onProvideVirtualStructure(ViewStructure structure) {

    }

    @Override
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        return new FakeAccessibilityNodeProvider();
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {

    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        return false;
    }

    @Override
    public void setOverScrollMode(int mode) {

    }

    @Override
    public void setScrollBarStyle(int style) {

    }

    @Override
    public void onDrawVerticalScrollBar(Canvas canvas, Drawable scrollBar, int l, int t, int r, int b) {

    }

    @Override
    public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {

    }

    @Override
    public void onWindowVisibilityChanged(int visibility) {

    }

    @Override
    public void onDraw(Canvas canvas) {

    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {

    }

    @Override
    public boolean performLongClick() {
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new FakeInputConnection();
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        return false;
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void onAttachedToWindow() {

    }

    @Override
    public void onDetachedFromWindow() {

    }

    @Override
    public void onVisibilityChanged(View changedView, int visibility) {

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {

    }

    @Override
    public void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {

    }

    @Override
    public boolean setFrame(int left, int top, int right, int bottom) {
        return false;
    }

    @Override
    public void onSizeChanged(int w, int h, int ow, int oh) {

    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        return false;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    }

    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        return false;
    }

    @Override
    public void setBackgroundColor(int color) {

    }

    @Override
    public void setLayerType(int layerType, Paint paint) {

    }

    @Override
    public void preDispatchDraw(Canvas canvas) {

    }

    @Override
    public void onStartTemporaryDetach() {

    }

    @Override
    public void onFinishTemporaryDetach() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public Handler getHandler(Handler originalHandler) {
        return mHandler.getOrCompute();
    }

    @Override
    public View findFocus(View originalFocusedView) {
        return originalFocusedView;
    }
}
