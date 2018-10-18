package com.sunyf713.dealcomplict.wearslidecomplict.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class ScrollDirector {
    public static final int NO_DIRECTION = 0;
    public static final int HORIZONTAL = 1;
    public static final int VERTICAL = 2;
    public static final int RETURN_FALSE = 3;
    public static final int RETURN_TRUE = 4;
    public static final int RETURN_YOURSELF = 5;
    private float lastX;
    private float lastY;
    private int direction = NO_DIRECTION;
    float denstX;
    float denstY;
    private ScrollControl.ChildScrollControlListener childlistener = null;
    public boolean canScrollXDispatch;
    private GestureDetector gestureDetector;
    private Context context;
    private GestureDetector.OnGestureListener gestureListener = new GestureDetector.OnGestureListener(){
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float denstX = e2.getRawX()-e1.getRawX();
            float denstY = e2.getRawY()-e1.getRawY();
            int direction = Math.abs(denstX)> Math.abs(denstY)?HORIZONTAL:VERTICAL;
            if(direction==HORIZONTAL&&childlistener!=null) {
                if (!canScrollXDispatch &&denstX>0) {
                    return false;
                }
                childlistener.onFlingHorizon(-velocityX, -velocityY);
                return true;
            }
            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            int direction = Math.abs(distanceX)> Math.abs(distanceY)?HORIZONTAL:VERTICAL;
            if(direction==HORIZONTAL&&childlistener!=null) {
                if (!canScrollXDispatch && distanceX < 0) {
                    return false;
                }
                childlistener.onScrollHorizon(distanceX);
                return true;
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }
    };

    public ScrollDirector(Context context){
        if(context==null)return;
        this.context = context;
        gestureDetector=new GestureDetector(context,gestureListener);
    }

    public void setChildScrollControlListener(ScrollControl.ChildScrollControlListener childlistener) {
        this.childlistener = childlistener;
    }


    public int getScrollDirection(){
        return direction;
    }




    public int handleTouchEvent(MotionEvent event){
        return RETURN_YOURSELF;
    }
    public int handleDispatchEvent(MotionEvent event){
        boolean isDeal;
        gestureDetector.onTouchEvent(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                if(childlistener!=null){
                    childlistener.beforeScroll();
                }
                if(childlistener!=null) {
                    canScrollXDispatch = childlistener.getXScrollBack();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float currentX = event.getRawX();
                float currentY = event.getRawY();
                denstX = lastX - currentX;
                denstY = lastY - currentY;

                float absX = Math.abs(denstX);
                float absY = Math.abs(denstY);
                direction = (absX - absY) > 0 ? HORIZONTAL: VERTICAL;
                if(direction == HORIZONTAL&&absX>10&&childlistener!=null) {
                    return RETURN_FALSE;
                }
                break;

            case MotionEvent.ACTION_UP:
                direction = NO_DIRECTION;
                break;
            case MotionEvent.ACTION_CANCEL:
                direction = NO_DIRECTION;
                break;
            default:
                break;

        }
        return RETURN_YOURSELF;
    }

}
