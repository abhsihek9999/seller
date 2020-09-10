package com.hk.seller.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.hk.seller.R;

import androidx.appcompat.widget.AppCompatTextView;

public class RPTextView extends AppCompatTextView {
    public RPTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RPView, 0, 0);
        String font = a.getString(R.styleable.RPView_Font);
        if (font != null) {
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font);
            if (typeface != null) {
                setTypeface(typeface);
            }
        }
        a.recycle();
    }

}