package com.sunyf713.dealcomplict.wearslidecomplict.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.DragEvent;
import android.view.MotionEvent;

public class ScrollControlRecyclerView extends RecyclerView {
    public int centerXScreen;
    public float startTouchXTouch;
    public boolean canScrollXTouch;


    public ScrollControlRecyclerView(Context context) {
        super(context);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        centerXScreen = displayMetrics.widthPixels/2;
    }

    public ScrollControlRecyclerView(Context context, @Nullable AttributeSet attrs){
        super(context,attrs);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        centerXScreen = displayMetrics.widthPixels/2;
    }

    public ScrollControlRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        centerXScreen = displayMetrics.widthPixels/2;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        return super.onHoverEvent(event);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }


    @Override
    public boolean onDragEvent(DragEvent event) {
        return super.onDragEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    private int HORIZONTAL = 1;
    private int VERTICAL = 2;
    private int NO_DIRECTION = 0;
    private float lastX;
    private float lastY;
    private int direction = 0;
    private int scrollY = 0;
    float denstX;
    float denstY;

    public float startTouchXDispatch;
    public float canScrollXDispatch;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public boolean canScrollVertically(int direction) {
        final int offset = computeVerticalScrollOffset();
        final int range = computeVerticalScrollRange() - computeVerticalScrollExtent();
        if (range == 0) return false;
        if (direction < 0) {
            return offset > 0;
        } else {
            return offset < range - 1;
        }
    }

    public boolean canScrollHorizontally(int direction) {
        final int offset = computeHorizontalScrollOffset();
        final int range = computeHorizontalScrollRange() - computeHorizontalScrollExtent();
        if (range == 0) return false;
        if (direction < 0) {
            return offset > 0;
        } else {
            return offset < range - 1;
        }
    }
}
