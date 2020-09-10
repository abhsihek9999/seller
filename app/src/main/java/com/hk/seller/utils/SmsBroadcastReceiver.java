package com.hk.seller.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "SmsBroadcastReceiver";
    private OTPReceiveListener OTPReceiveListener;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
            Bundle extras = intent.getExtras();
            Status mStatus = (Status) extras.get(SmsRetriever.EXTRA_STATUS);
            switch (mStatus.getStatusCode()) {
                case CommonStatusCodes.SUCCESS:
                    // Get SMS message contents'
                    String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                    if (OTPReceiveListener != null && message != null) {
                        String otp = getOPT(message);
                        OTPReceiveListener.onOTPReceived(otp);
                    }
                    break;
                case CommonStatusCodes.TIMEOUT:
                    // Waiting for SMS timed out (5 minutes)
                    if (OTPReceiveListener != null) {
                        OTPReceiveListener.onOTPTimeOut();
                    }
                    break;
            }
        }
    }

    private String getOPT(String message) {
        Pattern pattern = Pattern.compile("(\\d{4})");

//   \d is for a digit
//   {} is the number of digits here 4.


        if (message != null) {
            Matcher matcher = pattern.matcher(message);
            String val = "";
            if (matcher.find()) {
                val = matcher.group(0);  // 4 digit number
            }
            return val;
        } else {
            return "";
        }
    }

    public void setOnOtpListeners(OTPReceiveListener otpReceiveInterface) {
        this.OTPReceiveListener = otpReceiveInterface;
    }


}
