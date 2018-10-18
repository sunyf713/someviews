package com.sunyf713.dealcomplict.wearslidecomplict.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class ScrollControlRelativeLayout extends RelativeLayout {
    private ScrollDirector scrollDirector;
    public ScrollControlRelativeLayout(Context context) {
        super(context);
        init(context);
    }

    public ScrollControlRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScrollControlRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ScrollControlRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    public void init(Context context){
        scrollDirector = new ScrollDirector(context);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int result = scrollDirector.handleDispatchEvent(ev);
        switch (result){
            case ScrollDirector.RETURN_FALSE:
                return false;
            case ScrollDirector.RETURN_TRUE:
                return true;
            default:
                return super.dispatchTouchEvent(ev);
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


    public void setMoveControlChild(ScrollControl.ChildScrollControlListener listener) {
        scrollDirector.setChildScrollControlListener(listener);
    }
}
