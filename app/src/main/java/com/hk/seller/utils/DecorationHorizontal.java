package com.hk.seller.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DecorationHorizontal extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;
    private final int leftSpaceHeight;


    public DecorationHorizontal(int verticalSpaceHeight, int dimensionPixelSize) {
        this.verticalSpaceHeight = verticalSpaceHeight;
        leftSpaceHeight = dimensionPixelSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.right = leftSpaceHeight/* set your margin here */;
            return;
        }
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = leftSpaceHeight;
            outRect.right = verticalSpaceHeight;/* set your margin here */
            return;
        }

        outRect.right = verticalSpaceHeight;
    }
}