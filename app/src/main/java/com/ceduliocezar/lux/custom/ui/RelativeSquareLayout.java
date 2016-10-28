package com.ceduliocezar.lux.custom.ui;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class RelativeSquareLayout extends RelativeLayout {

    public static final double GOLDEN_RATIO = 1.61803398875;
    public RelativeSquareLayout(Context context) {
        super(context);
    }

    public RelativeSquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RelativeSquareLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), (int) (getMeasuredWidth()*GOLDEN_RATIO));
    }
}
