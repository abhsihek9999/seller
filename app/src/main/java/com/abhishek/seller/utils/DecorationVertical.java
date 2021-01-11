package com.abhishek.seller.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class DecorationVertical extends RecyclerView.ItemDecoration {

    private final int verticalSpaceHeight;
    private final int LeftSpaceHeight;


    public DecorationVertical(int verticalSpaceHeight, int i) {
        this.verticalSpaceHeight = verticalSpaceHeight;
        this.LeftSpaceHeight = i;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.left = LeftSpaceHeight;
            outRect.top = verticalSpaceHeight;
            outRect.right = LeftSpaceHeight;
            outRect.bottom = LeftSpaceHeight;
        }
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = 0/* set your margin here */;
            outRect.left = LeftSpaceHeight;
            outRect.right = LeftSpaceHeight;
            outRect.bottom = 0;

            return;
        }
        outRect.left = LeftSpaceHeight;
        outRect.top = verticalSpaceHeight;
        outRect.right = LeftSpaceHeight;
        outRect.bottom = 0;


    }
}