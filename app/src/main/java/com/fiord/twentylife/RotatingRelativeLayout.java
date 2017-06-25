package com.fiord.twentylife;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by fjord on 08.05.2017.
 */

public class RotatingRelativeLayout extends RelativeLayout {

    public RotatingRelativeLayout(Context context) {
        super(context);
    }

    public RotatingRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RotatingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RotatingRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }
}
