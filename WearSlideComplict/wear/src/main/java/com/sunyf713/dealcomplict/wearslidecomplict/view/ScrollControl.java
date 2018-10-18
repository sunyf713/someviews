package com.sunyf713.dealcomplict.wearslidecomplict.view;

public class ScrollControl {

    public interface ChildScrollControlListener {

        boolean onScrollHorizon(float dx);
        boolean onFlingHorizon(float velocityX, float velocityY);
        void beforeScroll();
        boolean getXScrollBack();
    }


    public interface ParentScrollControlListener {


    }

}
