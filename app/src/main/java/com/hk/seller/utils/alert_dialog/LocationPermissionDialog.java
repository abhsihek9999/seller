package com.hk.seller.utils.alert_dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hk.seller.R;

public class LocationPermissionDialog extends Dialog {

    public Context context;
    public Dialog dialog;
    public TextView btOk, no, titleTv;
    private TextView txtMsg;
    private String message;
    private String title;
    private String yesButton;


    private ConfirmationCallcack confirmationCallcack;

    public LocationPermissionDialog(Context context, String message, String title, String yesButton) {
        super(context, R.style.MyAlertDialogTheme);
        this.context = context;
        this.message = message;
        this.title = title;
        this.yesButton = yesButton;



    }

    public void setCallback(ConfirmationCallcack callback) {
        confirmationCallcack = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_location);
        btOk = findViewById(R.id.tv_continue);
        txtMsg = findViewById(R.id.tv_location_subheading);
        titleTv = findViewById(R.id.tv_enable_location);
        txtMsg.setText(message);
        btOk.setText(yesButton);
        titleTv.setText(title);


        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationCallcack.clickOkCallback();
                dismiss();
            }
        });


    }


    public interface ConfirmationCallcack {

        void clickOkCallback();


    }
}
