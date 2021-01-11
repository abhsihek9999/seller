package com.abhishek.seller.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;


public class MySeekBar extends androidx.appcompat.widget.AppCompatSeekBar {
    public MySeekBar (Context context) {
        super(context);
    }

    public MySeekBar (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MySeekBar (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        int thumb_x = (int) (( (double)this.getProgress()/this.getMax() ) * (double)this.getWidth());
        float middle = (float) (this.getHeight());

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        c.drawText(""+this.getProgress(), thumb_x, middle, paint);
    }
}