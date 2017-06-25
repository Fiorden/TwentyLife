package com.fiord.twentylife;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MySwipyRefreshLayout extends com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout{


    boolean down;

    public MySwipyRefreshLayout(Context context) {
        super(context);
    }

    public MySwipyRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                //down = false;
                return super.onInterceptTouchEvent(ev);
            case MotionEvent.ACTION_POINTER_DOWN:
                down = true;
                return super.onInterceptTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                //if (down == true)
                    //return true;
                    //else
                        return super.onInterceptTouchEvent(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }
}