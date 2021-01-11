package com.abhishek.seller.core.application.base_component;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        return textView;
    }


    protected abstract void init();
    protected abstract void setToolbar();

    protected abstract void setListener();

    protected abstract int getLayout();

}
