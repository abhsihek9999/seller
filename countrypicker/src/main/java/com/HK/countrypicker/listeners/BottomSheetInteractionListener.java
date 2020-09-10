package com.HK.countrypicker.listeners;

import android.view.View;

public interface BottomSheetInteractionListener {

  void initiateUi(View view);

  void setCustomStyle(View view);

  void setSearchEditText();

  void setupRecyclerView(View view);
}
