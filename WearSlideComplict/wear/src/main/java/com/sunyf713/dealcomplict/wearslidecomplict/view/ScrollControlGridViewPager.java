package com.sunyf713.dealcomplict.wearslidecomplict.view;

import android.content.Context;
import android.support.wearable.view.GridViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by yifei.sun on 16/11/17.
 */

public class ScrollControlGridViewPager extends GridViewPager {
    private ScrollDirector scrollDirector;
    private boolean canScrollVertical = true;

    public ScrollControlGridViewPager(Context context) {
        super(context);
        init(context);
    }

    public ScrollControlGridViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollControlGridViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context){
        scrollDirector = new ScrollDirector(context);
    }
    public void setCanScrollVertical(boolean canScrollVertical) {
        this.canScrollVertical = canScrollVertical;
    }
    public boolean isCanScrollVertical(){
        return canScrollVertical;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int result = scrollDirector.handleDispatchEvent(event);
        switch (result){
            case ScrollDirector.RETURN_FALSE:
                return false;
            case ScrollDirector.RETURN_TRUE:
                return true;
            default:
                return super.dispatchTouchEvent(event);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int result = scrollDirector.handleTouchEvent(ev);
        switch (result){
            case ScrollDirector.RETURN_FALSE:
                return false;
            case ScrollDirector.RETURN_TRUE:
                return true;
            default:
                return super.onTouchEvent(ev);
        }
    }
    @Override
    public void scrollTo(int x, int y) {
        if(scrollDirector.getScrollDirection() != ScrollDirector.VERTICAL|| canScrollVertical) {
            super.scrollTo(x, y);
        }
    }

    public void setMoveControlChild(ScrollControl.ChildScrollControlListener listener) {
        scrollDirector.setChildScrollControlListener(listener);
    }
}
