package com.hk.seller.utils;

public interface OTPReceiveListener {

        void onOTPReceived(String otp);

        void onOTPTimeOut();
    }