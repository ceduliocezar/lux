package com.ceduliocezar.lux;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by TECBMCCS on 01/06/16.
 */
public class LinearProporcional extends LinearLayout {
    public LinearProporcional(Context context) {
        super(context);
    }

    public LinearProporcional(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearProporcional(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), (int) ( getMeasuredWidth() * 1.61803398875)); //Snap to width
    }
}