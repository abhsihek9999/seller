package com.abhishek.seller.utils;

public interface OTPReceiveListener {

        void onOTPReceived(String otp);

        void onOTPTimeOut();
    }