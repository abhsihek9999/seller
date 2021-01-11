package com.abhishek.seller.core.application.base_component;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.abhishek.seller.R;

import androidx.annotation.Nullable;

public abstract class BaseModelFragment extends BottomSheetDialogFragment {

    public BaseModelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());

        return textView;
    }


    protected abstract void init();

    protected abstract void setToolbar();

    protected abstract void setListener();

    protected abstract int getLayout();

}
