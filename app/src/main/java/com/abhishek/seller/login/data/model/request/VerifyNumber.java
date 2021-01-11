package com.abhishek.seller.login.data.model.request;

import com.google.gson.annotations.SerializedName;
import com.abhishek.seller.BuildConfig;
import com.abhishek.seller.core.common.AppConstant;

import static com.abhishek.seller.utils.AppUtil.AndroidId;

public class VerifyNumber {
    @SerializedName("country_code")
    private String countryCode;

    @SerializedName("contact_no")
    private String contactNumber;

    @SerializedName("device_id")
    private String device_id;

    @SerializedName("app_version")
    private String app_version;

    @SerializedName("device_token")
    private String device_token;

    @SerializedName("otp")
    private String otp;

    public VerifyNumber(String countryCode, String contactNumber) {
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.platform = AppConstant.PLATFORM;
    }

    public VerifyNumber(String countryCode, String contactNumber, String otp, String device_token) {
        this.countryCode = countryCode;
        this.contactNumber = contactNumber;
        this.platform = AppConstant.PLATFORM;
        this.app_version = BuildConfig.VERSION_NAME;
        this.device_token = device_token;
        this.otp = otp;
        this.device_id = AndroidId();
    }

    @SerializedName("platform")
    private String platform;


}
